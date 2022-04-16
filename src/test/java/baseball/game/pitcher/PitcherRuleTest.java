package baseball.game.pitcher;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PitcherRuleTest {

    @DisplayName("숫자가 아닌 문자열이 입력될 경우 유효성 통과 안된다.")
    @ParameterizedTest
    @ValueSource(strings = {"", "12q", "abc", "aaaa", "ac2"})
    public void notValid_if_exists_none_numeric_char(String pitcherNumber) {
        assertThat(PitcherRule.isNotValid(pitcherNumber)).isTrue();
    }

    @DisplayName("3 자리 숫자가 아닌 경우 유효성 통과가 안된다.")
    @ParameterizedTest
    @ValueSource(strings = {"", "1234", "1", "12", "83"})
    public void notValid_if_exists_not_three_digits_char(String pitcherNumber) {
        assertThat(PitcherRule.isNotValid(pitcherNumber)).isTrue();
    }

    @DisplayName("중복된 수가 있을 경우 유효성 통과가 안된다.")
    @ParameterizedTest
    @ValueSource(strings = {"111", "122", "998", "131", "545"})
    public void notValid_if_exists_duplicated_number(String pitcherNumber) {
        assertThat(PitcherRule.isNotValid(pitcherNumber)).isTrue();
    }

    @DisplayName("0이 포함되어 있는 경우 유효성 통과가 안된다.")
    @ParameterizedTest
    @ValueSource(strings = {"102", "014", "092", "580", "000"})
    public void notValid_if_exists_zero_number(String pitcherNumber) {
        assertThat(PitcherRule.isNotValid(pitcherNumber)).isTrue();
    }

    @DisplayName("1~9 사이의 세자리 중복되지 않은 수일 때 유효성 통과")
    @ParameterizedTest
    @ValueSource(strings = {"123", "234", "965", "542", "631"})
    public void condition_all_satisfied(String pitcherNumber) {
        assertThat(PitcherRule.isNotValid(pitcherNumber)).isFalse();
    }

}
