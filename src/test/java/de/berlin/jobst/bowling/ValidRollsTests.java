package de.berlin.jobst.bowling;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;


// Calculate final score of a bowling game
//
// Specification: implement common bowling rules and calculate final score
//
// ** Steps **
// start a game
// roll (pins from 0 up to 10) until end (10 Frames, 2 rolls except last frame)
// calculate score

@RunWith(Parameterized.class)
public class ValidRollsTests {

    @Parameter
    public int[] rolls;

    @Parameter(1)
    public int score;

    @Parameters(name = "Run {index}: rolls={0}, expected score={1}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                // 1 frame
                {new int[]{0}, 0},
                {new int[]{1}, 1},
                {new int[]{10}, 10},

                {new int[]{0, 0}, 0},
                {new int[]{0, 1}, 1},
                {new int[]{0, 10}, 10},

                {new int[]{1, 0}, 1},
                {new int[]{1, 9}, 10},

                // 2 frames (no Spare/no Strike)
                {new int[]{0, 0, 0}, 0},
                {new int[]{1, 1, 1}, 3},

                {new int[]{0, 0, 0, 0}, 0},
                {new int[]{1, 1, 1, 1}, 4},
                {new int[]{8, 1, 8, 1}, 18},

                // 10 frames (no Spare/no Strike)
                // Minimum game
                {new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 0},
                {new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, 20},
                {new int[]{8, 1, 8, 1, 8, 1, 8, 1, 8, 1, 8, 1, 8, 1, 8, 1, 8, 1, 8, 1}, 90},

                // 2nd partial frame after spare
                {new int[]{9, 1, 1}, 12},
                // 2nd partial frame after strike
                {new int[]{10, 1}, 12},

                // 2nd frame after spare
                {new int[]{9, 1, 9, 1}, 29},
                // 2nd frame after strike
                {new int[]{10, 9, 1}, 30},
                // 2nd strike after strike
                {new int[]{10, 10, 5, 5}, 55},

                // Specification example
                {new int[]{1, 4, 4, 5, 6, 4, 5, 5, 10, 0, 1, 7, 3, 6, 4, 10, 2, 8, 6}, 133},

                // Maximum game
                {new int[]{10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10}, 300},
        });
    }

    @Test
    public void test() {
        assertEquals(score, Game.getScore(rolls));
    }
}
