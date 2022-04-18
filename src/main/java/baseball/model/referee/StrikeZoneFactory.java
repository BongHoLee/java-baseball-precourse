package baseball.model.referee;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.HashSet;
import java.util.Set;

public class StrikeZoneFactory {

    public static int[] createStrikeZone() {
        Set<Integer> strikeSet = new HashSet<>();
        addNoneDuplicatedStrikeNumberTo(strikeSet);

        return numericArrayStrikeZoneOf(strikeSet);
    }

    private static void addNoneDuplicatedStrikeNumberTo(Set<Integer> strikeSet) {
        while (strikeSet.size() < 3) {
            addStrikeTo(strikeSet);
        }
    }

    private static void addStrikeTo(Set<Integer> strikeSet) {
        int createdStrike = Randoms.pickNumberInRange(1, 9);
        strikeSet.add(createdStrike);
    }

    private static int[] numericArrayStrikeZoneOf(Set<Integer> strikeSet) {
        int[] numericStrike = new int[3];
        int i = 0;
        for (Integer eachStrike : strikeSet) {
            numericStrike[i] = eachStrike;
            i++;
        }

        return numericStrike;
    }
}
