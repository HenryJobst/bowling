package de.berlin.jobst.bowling;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;


@RunWith(Parameterized.class)
public class GameTestInvalidRolls {

    @Parameter
    public int[] rolls;

    @Parameters
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new int[]{-1}},
                {new int[]{11}},
                {new int[]{1, 10}},
                {new int[]{8, 2, 5, 5, 8, 2, 9, 1, 5, 4, 6, 4, 8, 1, 7, 2, 10, 4, 8}}
        });
    }

    @Test(expected = UnsupportedOperationException.class)
    public void test() {
        Game.getScore(rolls);
    }
}
