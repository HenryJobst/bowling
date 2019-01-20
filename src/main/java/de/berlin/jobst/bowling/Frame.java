package de.berlin.jobst.bowling;

class Frame {

    Frame(int frameIdx) {
        super();
        this.frameIdx = frameIdx;
    }

    boolean isLastFrame() {
        return frameIdx == 9;
    }

    int getScore() {
        int score = 0;
        for (int roll = 0; roll < rollIdx; roll++) {
            score += rolls[roll];
        }
        return score;
    }

    void rollBall(int i) {
        if ((i < 0) || (i > 10) || (!isLastFrame() && (getScore() + i) > 10)) {
            throw new IndexOutOfBoundsException("A roll of " + i + " is invalid.");
        }
        if (rollIdx > 2) {
            throw new IndexOutOfBoundsException("Invalid roll in frame.");
        }
        rolls[rollIdx] = i;
        rollIdx++;
    }

    boolean isFinished() {
        if (getScore() == 10) {
            return true;
        }
        return (rollIdx > 1);
    }

    boolean isSpare() {
        return (getScore() == 10 && rollIdx == 2);
    }

    boolean isStrike() {
        return (getScore() == 10 && rollIdx == 1);
    }

    int getFirstRoll() {
        return rolls[0];
    }

    int getSecondRoll() {
        return rolls[1];
    }

    boolean isRolled() {
        return rollIdx != 0;
    }

    private final int frameIdx;
    private final int[] rolls = new int[]{0, 0, 0};
    private int rollIdx = 0;
}
