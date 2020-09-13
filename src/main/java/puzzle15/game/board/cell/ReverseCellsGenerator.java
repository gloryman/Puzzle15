package puzzle15.game.board.cell;

import puzzle15.game.GameConst;

public class ReverseCellsGenerator implements CellsGenerator {
    @Override
    public int[][] apply(Integer height, Integer width) {
        int[][] cells = new int[height][width];

        int maxValue = width * height + 1;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                cells[i][j] = maxValue - (i * width + j + 1);
            }
        }

        cells[0][0] =  GameConst.BOARD_CURSOR_VALUE;
        return cells;
    }
}
