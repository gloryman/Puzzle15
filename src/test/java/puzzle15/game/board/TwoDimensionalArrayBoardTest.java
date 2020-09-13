package puzzle15.game.board;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import puzzle15.game.GameConst;

public class TwoDimensionalArrayBoardTest {
    private int testBoardDimension = GameConst.DEFAULT_BOARD_XY_LENGTH;
    private Board gameBoard = null;

    @Before
    public void setup() {
        int len = GameConst.DEFAULT_BOARD_XY_LENGTH;
        gameBoard = new TwoDimensionalArrayBoard(this::generateCellsWithCursorAtEnd, len, len);
    }

    @Test
    public void gameBoardShouldInit() {
        int width = testBoardDimension;
        int height = testBoardDimension;

        Board board = new TwoDimensionalArrayBoard(this::generateCellsWithCursorAtEnd, height, width);
        int[][] cells = board.getCells();

        // test Several cells
        Assert.assertEquals(1, cells[0][0]);
        Assert.assertEquals(GameConst.BOARD_CURSOR_VALUE, cells[height - 1][width - 1]); // Cursor

        Assert.assertEquals(width, board.getWidth());
        Assert.assertEquals(height, board.getHeight());
    }

    @Test
    public void shouldMoveOneLeft() {
        int initCursorX = testBoardDimension - 1;
        int initCursorY = testBoardDimension - 1;

        gameBoard.moveLeft();
        int[][] cells = gameBoard.getCells();

        // test Several cells
        Assert.assertEquals(1, cells[0][0]);
        Assert.assertEquals(GameConst.BOARD_CURSOR_VALUE, cells[initCursorY][initCursorX - 1]);
    }

    @Test
    public void shouldShiftLineRight() {
        gameBoard.moveRight();
        int[][] cells = gameBoard.getCells();

        // Check cursor position
        int cursorY = testBoardDimension - 1;
        int cursorX = 0;
        Assert.assertEquals(GameConst.BOARD_CURSOR_VALUE, cells[cursorY][cursorX]);

        // Check shifted Row
        int expectCellValue = testBoardDimension * testBoardDimension - 1;
        for (int i = testBoardDimension - 1; i > 0; i --) {
            Assert.assertEquals(expectCellValue, cells[cursorY][i]);
            expectCellValue --;
        }

    }

    @Test
    public void shouldMoveOneUp() {
        int cursorX = testBoardDimension - 1;
        int initCursorY = testBoardDimension - 1;

        int[][] cells = gameBoard.getCells();
        int swap = cells[initCursorY - 1][cursorX];

        gameBoard.moveUp();
        cells = gameBoard.getCells();
        Assert.assertEquals(GameConst.BOARD_CURSOR_VALUE, cells[initCursorY - 1][cursorX]);
        Assert.assertEquals(swap, cells[initCursorY][cursorX]);
    }

    @Test
    public void shouldShiftLineLeft() {
        int cursorX = testBoardDimension - 1;
        int cursorY = testBoardDimension - 1;
        int[][] cells = gameBoard.getCells();

        // Shift full row left by one.
        for (int i = 0; i < testBoardDimension - 1; i++) {
            gameBoard.moveLeft();
            cursorX --;
            if (i == gameBoard.getWidth()) {
                cursorX = gameBoard.getWidth();
            }

            Assert.assertEquals(GameConst.BOARD_CURSOR_VALUE, cells[cursorY][cursorX]);
        }

        int expectCellValue = testBoardDimension * testBoardDimension - 1;
        for (; cursorX > 0; cursorX--) {
            Assert.assertEquals(expectCellValue, cells[cursorY][cursorX]);
            expectCellValue --;
        }
    }

    @Test
    public void shouldShiftDown() {
        int cursorX = testBoardDimension - 1;
        int cursorY = testBoardDimension - 1;

        gameBoard.moveDown();

        // Check shifted column
        int[][] cells = gameBoard.getCells();
        for (; cursorY > 0; cursorY --) {
            int expectValue = (cursorY - 1) * gameBoard.getWidth() + gameBoard.getHeight();
            Assert.assertEquals(expectValue, cells[cursorY][cursorX]);
        }
        Assert.assertEquals(GameConst.BOARD_CURSOR_VALUE, cells[0][cursorX]);
    }

    @Test
    public void shouldShiftUp() {
        int cursorX = testBoardDimension - 1;
        int cursorY = testBoardDimension - 1;

        int[][] cells = gameBoard.getCells();
        for (;cursorY > 0; cursorY --) {
            gameBoard.moveUp();
            Assert.assertEquals(GameConst.BOARD_CURSOR_VALUE, cells[cursorY - 1][cursorX]);
        }

        gameBoard.moveUp();
        cursorY = testBoardDimension - 1;
        Assert.assertEquals(GameConst.BOARD_CURSOR_VALUE, cells[cursorY][cursorX]);
        for (cursorY = testBoardDimension - 2; cursorY > 0; cursorY --) {
            int expectValue = (cursorY) * gameBoard.getWidth() + gameBoard.getHeight();
            Assert.assertEquals(expectValue, cells[cursorY][cursorX]);
        }
    }


    @Test
    public void shouldMoveDown() {
        int[][] cells = gameBoard.getCells();
        int cursorX = testBoardDimension - 1;

        gameBoard.moveDown();
        Assert.assertEquals(GameConst.BOARD_CURSOR_VALUE, cells[0][cursorX]);

        gameBoard.moveDown();
        Assert.assertEquals(GameConst.BOARD_CURSOR_VALUE, cells[1][cursorX]);
    }

    private int[][] generateCellsWithCursorAtEnd(int sx, int sy) {
        int[][] cells = new int[sy][sx];

        for (int i = 0; i < sy; i++) {
            for (int j = 0; j < sx; j++      ) {
                cells[i][j] = i * sx + (j + 1);
            }
        }

        cells[sy - 1][sx - 1] = 0;
        return cells;
    }
}