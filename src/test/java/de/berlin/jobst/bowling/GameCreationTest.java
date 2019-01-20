package de.berlin.jobst.bowling;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class GameCreationTest {
	@Test
	public void startGame() {
		Game game = Game.startGame();
		assertTrue(game != null);
	}
}
