package puzzle15.console.ui;


import puzzle15.game.board.Board;
import java.util.function.Function;

public interface GameRender extends Function<Board, Void> {}
