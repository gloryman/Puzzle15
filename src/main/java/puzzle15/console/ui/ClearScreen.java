package puzzle15.console.ui;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

public class ClearScreen  {
    public Void apply() {
        Ansi screen = Ansi.ansi().eraseScreen();
        AnsiConsole.out().println(screen);
        return null;
    }
}
