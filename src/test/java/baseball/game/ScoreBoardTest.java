package baseball.game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import baseball.game.score.ScoreBoard;
import baseball.game.score.ScoreStatus;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ScoreBoardTest {

    private ScoreBoard scoreBoard = new ScoreBoard();


    @ParameterizedTest(name = "{index}: {2}")
    @MethodSource("validateParameters")
    @DisplayName("생성된 결과 문자열에 대한 유효성 검사")
    public void validate_buildFromScore(int ball, int strike, String expected) throws Exception {
        assertEquals(expected, buildResult(ballAndStrike(ball, strike)));
    }

    static Stream<Arguments> validateParameters() {
        return Stream.of(
                Arguments.of(1, 1, "1볼 1스트라이크"),
                Arguments.of(0, 0, "낫싱"),
                Arguments.of(0, 3, "3스트라이크"),
                Arguments.of(3, 0, "3볼"),
                Arguments.of(2, 1, "2볼 1스트라이크")
        );
    }

    private Map<ScoreStatus, Integer> ballAndStrike(int ball, int strike) {
        Map<ScoreStatus, Integer> scoreStatusMap = new HashMap<>();
        scoreStatusMap.put(ScoreStatus.BALL, ball);
        scoreStatusMap.put(ScoreStatus.STRIKE, strike);

        return scoreStatusMap;
    }

    private String buildResult(Map<ScoreStatus, Integer> scoreStatusMap) throws Exception {
        Method buildScoreFromMethod = scoreBoard.getClass().getDeclaredMethod("buildScoreFrom", Map.class);
        buildScoreFromMethod.setAccessible(true);

        return (String)buildScoreFromMethod.invoke(scoreBoard, scoreStatusMap);
    }
}
