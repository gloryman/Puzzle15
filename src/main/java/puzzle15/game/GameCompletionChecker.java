package puzzle15.game;
import puzzle15.game.board.Board;
import java.util.function.Function;

public interface GameCompletionChecker extends Function<Board, Boolean> {
}