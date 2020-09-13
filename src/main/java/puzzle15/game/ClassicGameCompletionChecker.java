package puzzle15.game;

import puzzle15.game.board.Board;

public class ClassicGameCompletionChecker implements GameCompletionChecker {
    @Override
    public Boolean apply(Board board) {
        int[][] cells = board.getCells();
        int width = board.getWidth();
        int height = board.getHeight();

        if (cells[height - 1][width - 1] != GameConst.BOARD_CURSOR_VALUE) {
            return false;
        }

        int expect = 1;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width - 1; x++) {
                if (expect > cells[y][x]) return false;
                expect++;
            }
        }

        // final column
        for (int x = 0; x < width - 1; x++) {
            if (expect > cells[height - 1][x]) return false;
            expect++;
        }

        return true;
    }
}
