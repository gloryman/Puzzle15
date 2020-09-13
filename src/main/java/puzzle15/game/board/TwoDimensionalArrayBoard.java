package puzzle15.game.board;

import puzzle15.game.GameConst;
import puzzle15.game.board.cell.CellsGenerator;

public class TwoDimensionalArrayBoard implements Board {
    private final int maxX, maxY;
    private int cursorX, cursorY;
    private final int[][] cells;

    public TwoDimensionalArrayBoard(CellsGenerator cellsProvider, int height, int width) {
        cells = cellsProvider.apply(height, width);
        this.maxX = width - 1;
        this.maxY = height - 1;

        int[] cursorYX = findCursor(cells);
        this.cursorY = cursorYX[0];
        this.cursorX = cursorYX[1];
    }

    @Override
    public void moveLeft() {
        if (cursorX <= 0) {
            shiftLeft();
            return;
        }
        cells[cursorY][cursorX] =  cells[cursorY][cursorX - 1];
        cursorX --;

        cells[cursorY][cursorX] = GameConst.BOARD_CURSOR_VALUE;
    }

    @Override
    public void moveRight() {
        if (cursorX >= maxX) {
            shiftRight();
            return;
        }
        cells[cursorY][cursorX] =  cells[cursorY][cursorX + 1];
        cursorX ++;

        cells[cursorY][cursorX] = GameConst.BOARD_CURSOR_VALUE;
    }

    @Override
    public void moveUp() {
        if (cursorY <= 0) {
            shiftUp();
            return;
        }

        cells[cursorY][cursorX] = cells[cursorY - 1][cursorX];
        cursorY --;
        cells[cursorY][cursorX] = GameConst.BOARD_CURSOR_VALUE;
    }

    @Override
    public void moveDown() {
        if (cursorY >= maxY) {
            shiftDown();
            return;
        }

        cells[cursorY][cursorX] = cells[cursorY + 1][cursorX];
        cursorY ++;
        cells[cursorY][cursorX] = GameConst.BOARD_CURSOR_VALUE;
    }

    @Override
    public int getWidth() {
        return maxX + 1;
    }

    @Override
    public int getHeight() {
        return maxY + 1;
    }

    @Override
    public int[][] getCells() {
        return cells;
    }

    private void shiftLeft() {
        for (int i = 0; i < maxX; i++) {
            cells[cursorY][i] = cells[cursorY][i + 1];
        }
        cursorX = maxX;
        cells[cursorY][cursorX] = GameConst.BOARD_CURSOR_VALUE;
    }

    private void shiftRight() {
        for (int i = maxX; i > 0; i--) {
            cells[cursorY][i] = cells[cursorY][i - 1];
        }
        cursorX = 0;
        cells[cursorY][cursorX] = GameConst.BOARD_CURSOR_VALUE;
    }

    private void shiftDown() {
        for (cursorY = maxY; cursorY > 0; cursorY--) {
            cells[cursorY][cursorX] = cells[cursorY - 1][cursorX];
        }
        cells[cursorY][cursorX] = GameConst.BOARD_CURSOR_VALUE;
    }

    private void shiftUp() {
        for (cursorY = 0; cursorY < maxY; cursorY++) {
            cells[cursorY][cursorX] = cells[cursorY + 1][cursorX];
        }
        cells[cursorY][cursorX] = GameConst.BOARD_CURSOR_VALUE;
    }

    private int[] findCursor(int[][] cells) {
        int[] result = new int[]{0, 0};

        for (int y = 0; y < cells.length; y++) {
            for (int x = 0; x < cells[0].length; x++) {
                if (cells[y][x] == GameConst.BOARD_CURSOR_VALUE) {
                    result[0] = y;
                    result[1] = x;

                    return result;
                }
            }
        }

        return result;
    }
}
