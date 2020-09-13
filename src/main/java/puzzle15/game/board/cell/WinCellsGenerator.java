package puzzle15.game.board.cell;


import puzzle15.game.GameConst;

public class WinCellsGenerator implements CellsGenerator {

    @Override
    public int[][] apply(Integer height, Integer width) {
        int[][] cells = new int[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                cells[i][j] = i * height + j + 1;
            }
        }

        cells[height - 1][width - 1] = GameConst.BOARD_CURSOR_VALUE;
        return cells;
    }
}
