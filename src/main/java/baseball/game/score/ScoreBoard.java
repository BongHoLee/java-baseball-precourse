package baseball.game.score;

import java.util.Map;

public class ScoreBoard {
    private final static String STRIKE = "스트라이크";
    private final static String BALL = "볼";
    private final static String NOTHING = "낫싱";

    public static void display(Map<ScoreStatus, Integer> scoreStatusMap) {
        String scoreResult = buildScoreFrom(scoreStatusMap);
        print(scoreResult);
    }

    private static String buildScoreFrom(Map<ScoreStatus, Integer> scoreStatusMap) {
        if (isNothing(scoreStatusMap)) {
            return nothingStatus();
        }

        return scoredStatus(scoreStatusMap);
    }

    private static boolean isNothing(Map<ScoreStatus, Integer> scoreStatusMap) {
        return scoreStatusMap.get(ScoreStatus.STRIKE) == 0 &&
                scoreStatusMap.get(ScoreStatus.BALL) == 0;
    }

    private static String nothingStatus() {
        return NOTHING;
    }

    private static String scoredStatus(Map<ScoreStatus, Integer> scoreStatusMap) {
        return buildBallStatus(scoreStatusMap)
                .append(space(scoreStatusMap))
                .append(buildStrikeStatus(scoreStatusMap))
                .toString();
    }

    private static StringBuilder buildBallStatus(Map<ScoreStatus, Integer> scoreStatusMap) {
        StringBuilder ballStatus = new StringBuilder();
        if (scoreStatusMap.get(ScoreStatus.BALL) != 0) {
            ballStatus
                    .append(scoreStatusMap.get(ScoreStatus.BALL))
                    .append(BALL);
        }

        return ballStatus;
    }

    private static StringBuilder space(Map<ScoreStatus, Integer> scoreStatusMap) {
        StringBuilder spaceStr = new StringBuilder();
        if (scoreStatusMap.get(ScoreStatus.BALL) != 0 && scoreStatusMap.get(ScoreStatus.STRIKE) != 0) {
            spaceStr.append(" ");
        }

        return spaceStr;
    }

    private static StringBuilder buildStrikeStatus(Map<ScoreStatus, Integer> scoreStatusMap) {
        StringBuilder strikeStatus = new StringBuilder();
        if (scoreStatusMap.get(ScoreStatus.STRIKE) != 0) {
            strikeStatus
                    .append((scoreStatusMap.get(ScoreStatus.STRIKE)))
                    .append(STRIKE);
        }

        return strikeStatus;
    }

    private static void print(String message) {
        System.out.println(message);
    }
}
