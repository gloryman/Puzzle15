package puzzle15.console;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import puzzle15.console.io.GameController;
import puzzle15.console.io.GameEvent;
import puzzle15.console.ui.GameRender;
import puzzle15.game.Game;
import puzzle15.game.GameConst;

@AllArgsConstructor
public class ConsoleLoop {
    private final static long DELAY = 300; // ms

    private GameRender render;
    private GameController gameController;
    private Game game;

    public void run() {
        executeIOEventLoop();

        //Main game Event Loop
        while (true) {
            GameEvent event = gameController.getNextEvent();
            GameConst.GameState state = game.move(event);
            if (event != null) continue; // in case we have more events

            render.apply(game.getGameBoard());
            if (GameConst.GameState.WON == state) {
                System.out.println(GameConst.PUZZLE_SOLVED_STRING);
                System.exit(0);
            }

            delay(DELAY);
        }
    }

    private void executeIOEventLoop() {
        Thread thread = new Thread(() -> {
            while (true) {
                gameController.collectUserInput();
                delay(DELAY / 3);
            }
        });
        thread.start();
    }

    @SneakyThrows
    private void delay(long delay) {
        Thread.sleep(delay);
    }
}
