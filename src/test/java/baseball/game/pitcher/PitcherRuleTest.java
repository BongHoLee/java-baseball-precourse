package baseball.game.pitcher;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class PitcherRuleTest {

    @ParameterizedTest(name = "{index}: {2}")
    @MethodSource("validateParameters")
    @DisplayName("전달된 pitcherNumber에 대한 유효성 검사")
    public void invalid_strikeZone(String pitcherNumber, boolean expected, String name) {
        assertEquals(expected, PitcherRule.isNotValid(pitcherNumber));
    }

    static Stream<Arguments> validateParameters() {
        return Stream.of(
            Arguments.of("", true, "공백 문자 입력 시 유효성 통과 실패"),
            Arguments.of("12q", true, "숫자가 아닐 시 유효성 통과 실패"),
            Arguments.of("1234", true, "3자리 숫자가 아닐 시 유효성 통과 실패"),
            Arguments.of("112", true, "중복 숫자가 존재 시 유효성 통과 실패"),
            Arguments.of("012", true, "0 존재 시 유효성 통과 실패"),
            Arguments.of("589", false, "1~9 사이의 중복 없는 세 자리 숫자로 구성 시 유효성 통과")
        );
    }

}
