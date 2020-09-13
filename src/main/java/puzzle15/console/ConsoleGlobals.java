package puzzle15.console;

import puzzle15.console.io.GameEvent;
import puzzle15.game.board.cell.CellsGenerator;
import puzzle15.game.board.cell.OneMoveSetup;
import puzzle15.game.board.cell.RandomCellsGenerator;
import puzzle15.game.board.cell.ReverseCellsGenerator;

import java.util.HashMap;
import java.util.Map;

public class ConsoleGlobals {
    public enum BoardPreset {
        RANDOM,
        REVERSE,
        SIMPLE
    }

    public static Map<BoardPreset, CellsGenerator> mapPresetOptionToCellsGenerator = new HashMap<BoardPreset, CellsGenerator>() {{
        put(BoardPreset.RANDOM, new RandomCellsGenerator());
        put(BoardPreset.REVERSE, new ReverseCellsGenerator());
        put(BoardPreset.SIMPLE, new OneMoveSetup());
    }};

    public static Map<String, GameEvent> KEYS_TO_EVENT_DICT = new HashMap<String, GameEvent>() {{
        put("q", GameEvent.EXIT);

        // Gamers move keys -> "W,A,S,D"
        put("w", GameEvent.MOVE_UP);
        put("s", GameEvent.MOVE_DOWN);
        put("a", GameEvent.MOVE_LEFT);
        put("d", GameEvent.MOVE_RIGHT);

        // VI bindings
        put("h", GameEvent.MOVE_LEFT);
        put("j", GameEvent.MOVE_DOWN);
        put("k", GameEvent.MOVE_UP);
        put("l", GameEvent.MOVE_RIGHT);
    }};
}
