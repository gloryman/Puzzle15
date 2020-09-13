package puzzle15.console;

import lombok.Builder;
import lombok.Data;
import puzzle15.game.board.Board;
import puzzle15.game.GameCompletionChecker;
import puzzle15.console.io.GameController;
import puzzle15.console.ui.GameRender;

@Data
@Builder
public class AppContext {
    private final Board board;
    private final GameRender gameRender;
    private final GameController gameController;
    private final GameCompletionChecker gameCompletionChecker;
}
