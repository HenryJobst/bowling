package de.berlin.jobst.bowling;

import java.util.Arrays;

class Game {

    static int[] transformSymbols(String in) {
        /*
            Idea to use this common representation and original python code by Juan L. Kehoe
            (https://juan0001.github.io/Calculate-the-bowling-score-using-a-machine-learning-model)

            Transform the rolls to scores based on the annotation of the symbols.
            Annotation of the symbols:
            "X" indicates a strike, "/" indicates a spare, "-" indicates a miss,
            and a number indicates the number of pins knocked down in the roll.
            For symbols:
            'X' -> 10
            '-' -> 0
            '/' -> '/' This will be kept the same to differentiate spare from strike.
            For numbers:
            Will transform the number in str to int.
        */

        int[] result = new int[21];
        char[] rolls = in.toCharArray();
        int idx = 0;
        for (char c : rolls) {
            // If it 's ' X ', it' s strike.Set the score to 10.
            if (c == 'X') result[idx] = 10;
                // If it 's ' - ', it' s missed.Set the score to 0.
            else if (c == '-') result[idx] = 0;
                // If it 's ' / ', it' s spare, keep it for the record.
            else if (c == '/') result[idx] = 10 - result[idx - 1];
            else result[idx] = Character.getNumericValue(c);
            idx++;
        }
        return Arrays.copyOf(result, idx);
    }

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
