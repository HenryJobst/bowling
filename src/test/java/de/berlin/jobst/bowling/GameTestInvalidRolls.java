package de.berlin.jobst.bowling;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;


@RunWith(Parameterized.class)
public class GameTestInvalidRolls {
	
	@Parameter
	public int[] rolls;

	@Parameters
	public static Iterable<Object[]> data() {
		return Arrays.asList(new Object[][] { 
			{ new int[] {-1} },
			{ new int[] {11} },
			{ new int[] {1, 10} },
			
			});
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void test() {
		Game game = Game.startGame();
		for (int i = 0; i < rolls.length; i++) {
			game.rollBall(rolls[i]);
		}
	}
}
