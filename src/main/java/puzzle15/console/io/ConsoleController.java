package puzzle15.console.io;


import org.jline.keymap.KeyMap;
import org.jline.reader.Binding;
import org.jline.reader.LineReader;
import org.jline.reader.Reference;
import org.jline.reader.impl.LineReaderImpl;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class ConsoleController implements GameController {
    private final Queue<GameEvent> eventsQueue = new LinkedList<>();
    private final LineReaderImpl ttyReader;
    private final KeyMap<Binding> ttyKeysMap;
    private Map<String, GameEvent> keysToEventDict;

    public ConsoleController(LineReaderImpl ttyReader, Map<String, GameEvent> keysToEventDict) {
        this.ttyReader = ttyReader;
        this.ttyKeysMap = ttyReader.getKeyMaps().get(LineReader.VIINS);
        this.keysToEventDict = keysToEventDict;
    }

    @Override
    public GameEvent getNextEvent() {
        synchronized (eventsQueue) {
            return eventsQueue.poll();
        }
    }

    @Override
    public void collectUserInput() {
        String keyBinding = pullKeyBinding(ttyKeysMap);
        GameEvent event = keysToEventDict.getOrDefault(keyBinding, GameEvent.UNKNOWN);
        queueUseEvent(event);
    }

    private String pullKeyBinding(KeyMap<Binding> map) {
        Binding binding = ttyReader.readBinding(map);

        if (binding instanceof Reference) {
            if (((Reference) binding).name().equals("vi-cmd-mode")) {    // Special case for `ESC`
                return "q";
            }
            return ttyReader.getLastBinding();
        }
        return null;
    }

    private void queueUseEvent(GameEvent gameEvent) {
        if (gameEvent == GameEvent.UNKNOWN) return;

        synchronized (eventsQueue) {
            eventsQueue.add(gameEvent);
        }
    }
}
