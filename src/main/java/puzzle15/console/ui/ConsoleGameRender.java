package puzzle15.console.ui;

import lombok.RequiredArgsConstructor;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;
import puzzle15.game.board.Board;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequiredArgsConstructor
public class ConsoleGameRender implements GameRender {
    private static final String TILE_TOP_LEFT = "┌";
    private static final String TILE_TOP_RIGHT = "┐";
    private static final String TILE_HORIZONTAL = "─";
    private static final String TILE_VERTICAL = "│";
    private static final String TILE_BOTTOM_LEFT = "└";
    private static final String TILE_BOTTOM_RIGHT = "┘";

    private final int cursorStartPositionRow;
    private final int cursorStartPositionColumn;

    /**
     * Call Puzzle15 board render:
     * ┌────┐┌────┐┌────┐┌────┐
     * │ 01 ││ 02 ││ 03 ││ 04 │
     * └────┘└────┘└────┘└────┘
     * ┌────┐┌────┐┌────┐┌────┐
     * │ 05 ││ 06 ││ 07 ││ 08 │
     * └────┘└────┘└────┘└────┘
     * ┌────┐┌────┐┌────┐┌────┐
     * │ 09 ││ 10 ││ 11 ││ 12 │
     * └────┘└────┘└────┘└────┘
     * ┌────┐┌────┐┌────┐┌────┐
     * │ 13 ││ 14 ││ 15 ││    │
     * └────┘└────┘└────┘└────┘
     */

    @Override
    public Void apply(Board board) {
        Ansi screen = Ansi.ansi()
                .cursor(cursorStartPositionRow, cursorStartPositionColumn);
        int valueWidth = (int) Math.log10(board.getHeight() * board.getWidth()) + 1;
        int[][] cells = board.getCells();

        for (int i = 0; i < board.getHeight(); i++){
            String[] rowValues = renderRow(cells[i], valueWidth);

            screen.saveCursorPosition()
                    .a(rowValues[0])
                    .restoreCursorPosition()
                    .cursorDown(1)
                    .a(rowValues[1])
                    .restoreCursorPosition()
                    .cursorDown(2)
                    .a(rowValues[2])
                    .restoreCursorPosition()
                    .cursorDown(3)
            ;
        }
        AnsiConsole.out().println(screen);
        return null;
    }

    private String[] renderRow(int[] row, int valueWidth) {
        int repeat = valueWidth + 2; // value surround with padding and vertical lines
        StringBuilder rowTop = new StringBuilder();
        StringBuilder mid = new StringBuilder();
        StringBuilder bottom = new StringBuilder();

        for (int i = 0; i < row.length; i++) {
            String tmp = String.format("%s%s%s", TILE_TOP_LEFT, stringRepeat(TILE_HORIZONTAL, repeat), TILE_TOP_RIGHT);
            rowTop.append(tmp);

            String strNumValue = row[i] > 0? String.format("%0" + valueWidth + "d", row[i]):
                    stringRepeat(" ", valueWidth);
            tmp = String.format("%s %s %s", TILE_VERTICAL, strNumValue, TILE_VERTICAL);
            mid.append(tmp);

            tmp = String.format("%s%s%s", TILE_BOTTOM_LEFT, stringRepeat(TILE_HORIZONTAL, repeat), TILE_BOTTOM_RIGHT);
            bottom.append(tmp);
        }

        return new String[]{rowTop.toString(), mid.toString(), bottom.toString()};
    }


    private String stringRepeat(String template, int count) {
        List<String> repeatedList = IntStream.range(0, count).boxed().map(i -> template).collect(Collectors.toList());
        return String.join("", repeatedList);
    }
}
