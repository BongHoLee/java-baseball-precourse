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
                Arguments.of(new int[] {1, 2, 3}, new int[] {1, 2, 3}, "0B 3S", "스트라이크존 123, 피쳐볼 123일 때 [0B 3S]" ),
                Arguments.of(new int[] {1, 2, 3}, new int[] {1, 2, 5}, "0B 2S", "스트라이크존 123, 피쳐볼 125일 때 [0B 2S]" ),
                Arguments.of(new int[] {2, 7, 5}, new int[] {2, 5, 7}, "2B 1S", "스트라이크존 275, 피쳐볼 257일 때 [2B 1S]" ),
                Arguments.of(new int[] {1, 2, 3}, new int[] {4, 5, 6}, "0B 0S", "스트라이크존 123, 피쳐볼 456일 때 [0B 0S]" ),
                Arguments.of(new int[] {8, 7, 3}, new int[] {8, 1, 7}, "1B 1S", "스트라이크존 873, 피쳐볼 817일 때 [1B 1S]" ),
                Arguments.of(new int[] {5, 4, 9}, new int[] {9, 5, 4}, "3B 0S", "스트라이크존 549, 피쳐볼 954일 때 [3B 0S]" )
        );
    }

    private String scoring(int[] strikeZone, int[] pitchNumbers) throws Exception {
        setStrikeZone(strikeZone);
        setPitchNumbers(pitchNumbers);

        return result();
    }

    private void setStrikeZone(int[] strikeZone) throws Exception{
        Field strikeZoneField = referee.getClass().getDeclaredField("strikeZone");
        strikeZoneField.setAccessible(true);
        strikeZoneField.set(referee, strikeZone);
    }

    private void setPitchNumbers(int[] pitchNumbers) {
        when(pitcher.pitch()).thenReturn(pitchNumbers);
    }

    private String result() {
        Map<ScoreStatus, Integer> scoreStatusMap = referee.playBall();

        return scoreStatusMap.get(ScoreStatus.BALL) + "B " + scoreStatusMap.get(ScoreStatus.STRIKE) + "S";
    }
}
