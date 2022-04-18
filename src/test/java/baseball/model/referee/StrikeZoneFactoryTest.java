package baseball.model.referee;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

public class StrikeZoneFactoryTest {

    private int[] strikeZone;

    @BeforeEach
    void setUp() {
        strikeZone = StrikeZoneFactory.createStrikeZone();
    }

    @RepeatedTest(100)
    @DisplayName("생성된 strikeZone이 123 ~ 987 사이의 수로 구성되는지 테스트")
    public void check_strikeZone_is_between_123_and_987() {
        int strikeZoneNumber = 0;
        int digit = 100;
        for (int eachStrike : strikeZone) {
            strikeZoneNumber += eachStrike * digit;
            digit = digit/10;
        }

        assertThat(strikeZoneNumber).isBetween(123, 987);
    }

    @RepeatedTest(100)
    @DisplayName("생성된 stirkeZone에 중복된 수가 존재하지 않는지 테스트")
    public void check_strikeZone_is_not_duplicated() {
        Set<Integer> strikeSet = new HashSet<>();

        for (int eachStrike : strikeZone) {
            strikeSet.add(eachStrike);
        }

        assertThat(strikeSet.size()).isEqualTo(strikeZone.length);
    }

}
