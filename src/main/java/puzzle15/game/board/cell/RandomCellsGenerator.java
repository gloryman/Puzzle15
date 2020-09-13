package puzzle15.game.board.cell;

import puzzle15.game.GameConst;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.Collections;

public class RandomCellsGenerator implements CellsGenerator {
    @Override
    public int[][] apply(Integer height, Integer width) {
        int[][] cells = new int[height][width];
        List<Integer> boardSource = shuffledList(height, width);
        while(!isSolvable(boardSource, width)) Collections.shuffle(boardSource);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                cells[i][j] = boardSource.get(i * width + j);
            }
        }

        return cells;
    }

    protected boolean isSolvable(List<Integer> boardSource, Integer width) {
        int height = boardSource.size() / width;
        int cursorRow = boardSource.indexOf(GameConst.BOARD_CURSOR_VALUE) / width + 1;
        int invCount = getInversionsCount(boardSource);

        if ((height & 1) > 0)               // Grid is odd
            return (invCount & 1) == 0;

        // Even
        if ((cursorRow & 1) == 0)
            return (invCount & 1) != 0;

        return (invCount & 1) == 0;
    }

    private int getInversionsCount(List<Integer> boardSource) {
        int invCount = 0;
        for (int i = 0; i < boardSource.size(); i++) {
            for (int j = i + 1; j < boardSource.size(); j++) {
                if (boardSource.get(j) > GameConst.BOARD_CURSOR_VALUE &&
                        boardSource.get(i) >  GameConst.BOARD_CURSOR_VALUE &&
                        boardSource.get(i) > boardSource.get(j)) {
                    invCount++;
                }
            }
        }

        return invCount;
    }


    private List<Integer> shuffledList( Integer height, Integer width) {
        List<Integer> boardSource = IntStream.range(0, (height * width))
                .boxed()
                .collect(Collectors.toList());
        Collections.shuffle(boardSource);
        return boardSource;
    }
}
