package puzzle15.console.io;

import org.jline.keymap.KeyMap;
import org.jline.reader.Binding;
import org.jline.reader.Reference;
import org.jline.reader.impl.LineReaderImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import puzzle15.console.ConsoleGlobals;

import java.util.HashMap;
import java.util.Map;

public class ConsoleControllerTest {
    private Map<String, KeyMap< Binding >> readerKeyMaps;
    private LineReaderImpl lineReader;

    @Before
    public void setUp() {
        readerKeyMaps = new HashMap<>();
        lineReader = Mockito.mock(LineReaderImpl.class);
        Mockito.when(lineReader.getKeyMaps()).thenReturn(readerKeyMaps);
    }

    @Test
    public void shouldNoReceiveValue() {
        ConsoleController controller = new ConsoleController(lineReader, ConsoleGlobals.KEYS_TO_EVENT_DICT);

        ConsoleGlobals.KEYS_TO_EVENT_DICT
                .forEach((key, value) -> {
                    Reference referance = new Reference(key);
                    Mockito.when(lineReader.readBinding(null)).thenReturn(referance);
                    Mockito.when(lineReader.getLastBinding()).thenReturn(key);

                    controller.collectUserInput();
                    GameEvent eventValue = controller.getNextEvent();

                    Assert.assertEquals(value, eventValue);
                });
    }
}