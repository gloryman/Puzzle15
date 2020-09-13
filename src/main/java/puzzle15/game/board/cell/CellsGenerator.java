package puzzle15.game.board.cell;

import java.util.function.BiFunction;

/**
 * Implemenmtation of this interface must produce arra of board cells for `Puzzle15`
 */
public interface CellsGenerator extends BiFunction<Integer, Integer, int[][]> {
}
