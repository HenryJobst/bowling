package de.berlin.jobst.bowling;

public class Frame {
	
	public Frame(int frameIdx) {
		super();
		this.frameIdx = frameIdx;
	}

	public boolean isLastFrame() {
		return frameIdx == 9;
	}
	
	public int getScore() {
		int score = 0;
		for (int roll = 0; roll < rollIdx; roll++) {
			score += rolls[roll];
		}
		return score;
	}

	public void rollBall(int i) {
		if ((i < 0) || (i > 10) || (!isLastFrame() && (getScore() + i) > 10)) {
			throw new IndexOutOfBoundsException("A roll of " + i + " is invalid.");
		}
		if (rollIdx > 2) {
			throw new IndexOutOfBoundsException("Invalid roll in frame.");
		}
		rolls[rollIdx] = i;
		rollIdx++;
	}

	public boolean isFinished() {
		if (getScore() == 10) {
			return true;
		}
		return (rollIdx > 1);
	}

	public boolean isSpare() {
		return (getScore() == 10 && rollIdx == 2);
	}
	
	public boolean isStrike() {
		return (getScore() == 10 && rollIdx == 1);
	}

	public int getFirstRoll() {
		return rolls[0];
	}

	public int getSecondRoll() {
		return rolls[1];
	}
	
	public boolean isRolled() {
		return rollIdx != 0;
	}
	
	private int frameIdx = 0; 
	private int[] rolls = new int[] { 0, 0, 0 };
	private int rollIdx = 0;
}
