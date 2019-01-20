package de.berlin.jobst.bowling;

class Game {

    static int getScore(int[] rolls) {
        Game game = new Game();
        for (int roll : rolls) {
            game.rollBall(roll);
        }
        return game.getScore();
    }

    private void rollBall(int i) {
        Frame frame = frames[frameIdx];
        if (frame == null) {
            frame = new Frame(frameIdx);
            frames[frameIdx] = frame;
        }
        frame.rollBall(i);
        if (frame.isFinished() && !isFinished()) {
            frameIdx++;
            frames[frameIdx] = new Frame(frameIdx);
        }
    }

    private boolean isFinished() {
        return (frames[frameIdx].isLastFrame() && frames[frameIdx].isFinished());
    }

    private int getScore() {
        System.out.println("********** Game **********");
        int score = 0;
        for (int idx = 0; idx <= frameIdx; idx++)
            score = getFrameScore(score, idx);
        return score;
    }

    private int getFrameScore(int score, int idx) {
        Frame frame = at(idx);
        if (frame == null)
            return score;
        score += frame.getScore();
        score = getBonusScore(score, idx);
        if (frame.isRolled()) {
            System.out.println("Frame: " + (idx + 1) + " Score: " + score);
        }
        return score;
    }

    private int getBonusScore(int score, int idx) {
        Frame frame = at(idx);
        int nextIdx = idx + 1;
        if ((frame.isSpare() || frame.isStrike()) && !frame.isLastFrame()) {
            Frame nextFrame = at(nextIdx);
            if (frame.isSpare()) {
                score += nextFrame.getFirstRoll();
            } else if (frame.isStrike()) {
                if (nextFrame.isStrike()) {
                    score += nextFrame.getFirstRoll();
                    if (nextFrame.isLastFrame()) {
                        score += nextFrame.getSecondRoll();
                    } else {
                        score += at(idx + 2).getFirstRoll();
                    }
                } else {
                    score += nextFrame.getFirstRoll() + nextFrame.getSecondRoll();
                }
            }
        }
        return score;
    }

    private Frame at(int idx) {
        return frames[idx];
    }

    private final Frame[] frames = new Frame[10];
    private int frameIdx = 0;
}
