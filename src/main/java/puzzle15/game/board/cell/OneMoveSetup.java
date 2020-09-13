package puzzle15.game.board.cell;

import puzzle15.game.GameConst;

public class OneMoveSetup implements CellsGenerator {
    @Override
    public int[][] apply(Integer height, Integer width) {
        int[][] cells = new WinCellsGenerator().apply(height, width);

        // Swap one element
        cells[height - 1][width - 1] = cells[height - 1][width - 2];
        cells[height - 1][width - 2] = GameConst.BOARD_CURSOR_VALUE;

        return cells;

    }
}
