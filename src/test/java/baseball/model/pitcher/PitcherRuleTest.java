package baseball.model.pitcher;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class PitcherRuleTest {

    @ParameterizedTest(name = "{index}: {1}")
    @MethodSource("throwExceptionParameter")
    @DisplayName("유효하지 않은 pitcherNumber에 대한 예외 발생 시 통과")
    public void not_valid_pitcherNumber_test(String pitcherNumbers, String name) {
        String NOT_VALID_ERROR_MESSAGE = "유효하지 않은 값 입니다. 게임을 종료합니다.";

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> PitcherRule.validationCheck(pitcherNumbers));
        assertThat(e.getMessage()).isEqualTo(NOT_VALID_ERROR_MESSAGE);

    }

    static Stream<Arguments> throwExceptionParameter() {
        return Stream.of(
            Arguments.of("", "공백 문자 입력 시 유효성 통과 실패"),
            Arguments.of("12q", "숫자가 아닐 시 유효성 통과 실패"),
            Arguments.of("1234", "3자리 숫자가 아닐 시 유효성 통과 실패"),
            Arguments.of("112", "중복 숫자가 존재 시 유효성 통과 실패"),
            Arguments.of("012", "0 존재 시 유효성 통과 실패")
        );
    }

    @ParameterizedTest(name = "{index}: {1}")
    @MethodSource("doesNotThrowExceptionParameter")
    @DisplayName("유효한 pitcherNumber에 대해 예외 발생하지 않을 시 통과")
    public void valid_pitcherNumber_test(String pitcherNumbers, String name) {
        assertDoesNotThrow(() -> PitcherRule.validationCheck(pitcherNumbers));
    }

    static Stream<Arguments> doesNotThrowExceptionParameter() {
        return Stream.of(
                Arguments.of("123", "123 통과"),
                Arguments.of("124", "124 통과"),
                Arguments.of("358", "358 통과"),
                Arguments.of("591", "591 통과")
        );
    }
}
