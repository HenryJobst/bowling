package de.berlin.jobst.bowling;

class Frame {

    private final int frameIdx;
    private final int[] rolls = new int[]{0, 0, 0};
    private int rollIdx = 0;

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
        int score = getScore();
        if (isLastFrame()) {
            if (isSpare()) {
                score -= 10;
            } else {
                if (getFirstRoll() == 10) {
                    score -= 10;
                }
                if (getSecondRoll() == 10) {
                    score -= 10;
                }
            }
        }

        if ((i < 0) || (i > 10) || ((score + i) > 10)) {
            throw new UnsupportedOperationException("A roll of " + i + " is invalid.");
        }

        if (rollIdx > 2) {
            throw new UnsupportedOperationException("Invalid roll in frame.");
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
        return (getScore() == 10 && rollIdx == 1 || getScore() == 20 && rollIdx == 2);
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

    int getRoll() {
        return rollIdx + 1;
    }
}
