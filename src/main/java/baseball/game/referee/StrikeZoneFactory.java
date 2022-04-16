package baseball.game.referee;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.HashSet;
import java.util.Set;

public class StrikeZoneFactory {

    public static int createStrikeZone() {
        Set<Integer> strikeSet = new HashSet<>();
        while (strikeSet.size() < 3) {
            addStrikeTo(strikeSet);
        }
        return numericStrikeZoneOf(strikeSet);
    }

    private static void addStrikeTo(Set<Integer> strikeSet) {
        int createdStrike = Randoms.pickNumberInRange(1, 9);
        strikeSet.add(createdStrike);
    }

    private static int numericStrikeZoneOf(Set<Integer> strikeSet) {
        int numericStrike = 0;

        int eachDigit = 1;
        for (Integer eachStrike : strikeSet) {
            numericStrike += eachStrike * eachDigit;
            eachDigit *= 10;
        }

        return numericStrike;
    }
}
