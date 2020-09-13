package puzzle15.game.board.cell;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RandomCellsGeneratorTest {
    private RandomCellsGenerator randomCellsGenerator = new RandomCellsGenerator();

    @Test
    public void shouldBeSolvable() {
        List<Integer> integers = Arrays.asList(
                3, 9, 1, 15,
                14, 11, 4, 6,
                13, 0, 10, 12,
                2, 7, 8, 5);
        boolean isSolvable = randomCellsGenerator.isSolvable(integers, 4);
        Assert.assertTrue(isSolvable);
    }

    @Test
    public void shouldNotBeSolvable() {
        List<Integer> integers = Arrays.asList(
                6, 13, 7, 10,
                8, 9, 11, 0,
                15, 2, 12, 5,
                14, 3, 1, 4
        );

        boolean isSolvable = randomCellsGenerator.isSolvable(integers, 4);
        Assert.assertFalse(isSolvable);
    }

    @Test
    public void shouldBeSolvableOnNonStandartMap() {
        List<Integer> integers = Arrays.asList(
                1, 8, 2,
                0, 4, 3,
                7, 6, 5
        );

        boolean isSolvable = randomCellsGenerator.isSolvable(integers, 3);
        Assert.assertTrue(isSolvable);
    }

    @Test
    public void testRandomizer() {
        int testHeight = 10;
        int testWidth = 10;

        int[][] array = randomCellsGenerator.apply(testHeight, testWidth);
        List<Integer> cellsSequence = Arrays.stream(array)
                .flatMapToInt(Arrays::stream)
                .boxed().collect(Collectors.toList());

        boolean isSolvable = randomCellsGenerator.isSolvable(cellsSequence, testWidth);
        Assert.assertTrue(isSolvable);
    }

}