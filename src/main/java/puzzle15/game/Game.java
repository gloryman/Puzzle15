package puzzle15.game;

import puzzle15.game.board.Board;
import puzzle15.console.io.GameEvent;

import java.util.HashMap;
import java.util.Map;

public class Game {
    private final Map<GameEvent, Runnable> boardsActionsDict;
    private final Board gameBoard;
    private final GameCompletionChecker gameCompletionChecker;

    public Game(Board gameBoard, GameCompletionChecker gameCompletionChecker) {
        this.gameBoard = gameBoard;
        this.gameCompletionChecker = gameCompletionChecker;

        boardsActionsDict = new HashMap<GameEvent, Runnable>() {{
            put(GameEvent.MOVE_LEFT, gameBoard::moveLeft);
            put(GameEvent.MOVE_RIGHT, gameBoard::moveRight);
            put(GameEvent.MOVE_UP, gameBoard::moveUp);
            put(GameEvent.MOVE_DOWN, gameBoard::moveDown);
            put(GameEvent.EXIT, () -> exit());
        }};
    }

    public Board getGameBoard() {
        return gameBoard;
    }

    public GameConst.GameState move(GameEvent gameEvent) {
        boardsActionsDict.getOrDefault(gameEvent, () -> {}).run();
        if (gameCompletionChecker.apply(gameBoard)) {
            return GameConst.GameState.WON;
        }

        return GameConst.GameState.IN_PROGRESS;
    }

    private void exit() {
        System.exit(0);
    }
}
