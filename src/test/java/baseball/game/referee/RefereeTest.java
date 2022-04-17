package baseball.game.referee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import baseball.game.pitcher.Pitcher;
import baseball.game.score.ScoreStatus;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class RefereeTest {

    private Referee referee;
    private Pitcher pitcher;

    @BeforeEach
    public void setUp() {
        pitcher = mock(Pitcher.class);
        referee = new Referee(pitcher);
    }


    @ParameterizedTest(name = "{index}: {3}")
    @MethodSource("validateParameters")
    @DisplayName("산출된 게임 결과에 대한 유효성 검사")
    public void refereeTest(int[] strikeZone, int[] pitchNumbers, String expected, String name) throws Exception {
        assertEquals(expected, scoring(strikeZone, pitchNumbers));
    }

    static Stream<Arguments> validateParameters() {
        return Stream.of(
                Arguments.of(new int[] {1, 2, 3}, new int[] {1, 2, 3}, "3스트라이크", "스트라이크존 123, 피쳐볼 123일 때 3스트라이크" ),
                Arguments.of(new int[] {1, 2, 3}, new int[] {1, 2, 5}, "2스트라이크", "스트라이크존 123, 피쳐볼 125일 때 2스트라이크" ),
                Arguments.of(new int[] {2, 7, 5}, new int[] {2, 5, 7}, "2볼 1스트라이크", "스트라이크존 275, 피쳐볼 257 때 2볼 1스트라이크" ),
                Arguments.of(new int[] {1, 2, 3}, new int[] {4, 5, 6}, "낫싱", "스트라이크존 123, 피쳐볼 456 때 낫싱" ),
                Arguments.of(new int[] {8, 7, 3}, new int[] {8, 1, 7}, "1볼 1스트라이크", "스트라이크존 873, 피쳐볼 817 때 1볼 1스트라이크" ),
                Arguments.of(new int[] {5, 4, 9}, new int[] {9, 5, 4}, "3볼", "스트라이크존 549, 피쳐볼 954 때 3볼" )
        );
    }

    private String scoring(int[] strikeZone, int[] pitchNumbers) throws Exception {
        setStrikeZone(strikeZone);
        setPitchNumbers(pitchNumbers);

        return printResult();
    }

    private void setStrikeZone(int[] strikeZone) throws Exception{
        Field strikeZoneField = referee.getClass().getDeclaredField("strikeZone");
        strikeZoneField.setAccessible(true);
        strikeZoneField.set(referee, strikeZone);
    }

    private void setPitchNumbers(int[] pitchNumbers) {
        when(pitcher.pitch()).thenReturn(pitchNumbers);
    }

    private String printResult() {
        StringBuilder result = new StringBuilder();
        Map<ScoreStatus, Integer> scoreStatusMap = referee.playBall();

        return buildResult(scoreStatusMap);
    }

    private String buildResult(Map<ScoreStatus, Integer> scoreStatusMap) {
        if (isNothing(scoreStatusMap)) {
            return buildNothingStatus();
        }

        return scored(scoreStatusMap);
    }

    private boolean isNothing(Map<ScoreStatus, Integer> scoreStatusMap) {
        return scoreStatusMap.get(ScoreStatus.STRIKE) == 0 &&
                scoreStatusMap.get(ScoreStatus.BALL) == 0;
    }

    private String scored(Map<ScoreStatus, Integer> scoreStatusMap) {
        return buildBallStatus(scoreStatusMap)
                .append(space(scoreStatusMap))
                .append(buildStrikeStatus(scoreStatusMap))
                .toString();
    }

    private String buildNothingStatus() {
        return "낫싱";
    }

    private StringBuilder buildStrikeStatus(Map<ScoreStatus, Integer> scoreStatusMap) {
        StringBuilder strikeStatus = new StringBuilder();
        if (scoreStatusMap.get(ScoreStatus.STRIKE) != 0) {
            strikeStatus
                    .append((scoreStatusMap.get(ScoreStatus.STRIKE)).toString())
                    .append(ScoreStatus.STRIKE);
        }

        return strikeStatus;
    }

    private StringBuilder buildBallStatus(Map<ScoreStatus, Integer> scoreStatusMap) {
        StringBuilder ballStatus = new StringBuilder();
        if (scoreStatusMap.get(ScoreStatus.BALL) != 0) {
            ballStatus
                    .append((scoreStatusMap.get(ScoreStatus.BALL)).toString())
                    .append(ScoreStatus.BALL);
        }

        return ballStatus;
    }

    private StringBuilder space(Map<ScoreStatus, Integer> scoreStatusMap) {
        StringBuilder spaceStr = new StringBuilder();
        if (scoreStatusMap.get(ScoreStatus.BALL) != 0 && scoreStatusMap.get(ScoreStatus.STRIKE) != 0) {
            spaceStr.append(" ");
        }

        return spaceStr;
    }

}
