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

        });
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void test() {
        Game.getScore(rolls);
    }
}
