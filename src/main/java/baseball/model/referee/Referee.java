package baseball.model.referee;

import baseball.model.pitcher.Pitcher;
import baseball.status.ScoreStatus;
import java.util.HashMap;
import java.util.Map;

public class Referee {
    private final Pitcher pitcher;
    private final int[] strikeZone;
    private Map<ScoreStatus, Integer> scoringMap = new HashMap<>();

    public Referee(Pitcher pitcher) {
        this.pitcher = pitcher;
        this.strikeZone = StrikeZoneFactory.createStrikeZone();
        initScoringMap();
    }

    public Map<ScoreStatus, Integer> playBall() {
        initScoringMap();
        scoring(pitcher.pitch());

        return scoringMap;
    }

    private void initScoringMap() {
        scoringMap = new HashMap<>();
        scoringMap.put(ScoreStatus.STRIKE, 0);
        scoringMap.put(ScoreStatus.BALL, 0);
    }

    private void scoring(int[] pitchNumbers) {
        for (int pitchIdx = 0; pitchIdx < pitchNumbers.length; pitchIdx++) {
            Pair eachPitch = new Pair(pitchIdx, pitchNumbers[pitchIdx]);
            scoringStrikeZoneWith(eachPitch);
        }
    }

    private void scoringStrikeZoneWith(Pair eachPitch) {
        for (int strikeIdx = 0; strikeIdx < strikeZone.length; strikeIdx++) {
            Pair eachStrike = new Pair(strikeIdx, strikeZone[strikeIdx]);
            compareEachAndScoring(eachPitch, eachStrike);
        }
    }

    private void compareEachAndScoring(Pair eachPitch, Pair eachStrike) {
        if (eachPitch.number == eachStrike.number) {
            scoringForIdx(eachPitch.idx, eachStrike.idx);
        }
    }

    private void scoringForIdx(int pitchIdx, int strikeIdx) {
        strikeIfIndicesMatched(pitchIdx, strikeIdx);
        ballIfIndicesNotMatched(pitchIdx, strikeIdx);
    }

    private void strikeIfIndicesMatched(int pitchIdx, int strikeIdx) {
        if (pitchIdx == strikeIdx) {
            scoringMap.put(ScoreStatus.STRIKE, scoringMap.get(ScoreStatus.STRIKE) + 1);
        }
    }

    private void ballIfIndicesNotMatched(int pitchIdx, int strikeIdx) {
        if (pitchIdx != strikeIdx) {
            scoringMap.put(ScoreStatus.BALL, scoringMap.get(ScoreStatus.BALL) + 1);
        }
    }

    private static class Pair {
        private final int idx;
        private final int number;

        public Pair(int idx, int number) {
            this.idx = idx;
            this.number = number;
        }
    }
}
