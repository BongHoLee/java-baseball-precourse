package baseball.game.pitcher;

import java.util.HashSet;
import java.util.Set;

public class PitcherRule {

    public static boolean isNotValid(String pitcherString) {
        return isNotThreeLetter(pitcherString) ||
                isNotNumber(pitcherString) ||
                hasDuplicatedLetter(pitcherString) ||
                hasZeroNumber(pitcherString);

    }

    private static boolean isNotThreeLetter(String pitcherString) {
        return pitcherString.length() != 3;
    }

    private static boolean isNotNumber(String pitcherString) {
        try {
            Integer.parseInt(pitcherString);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    private static boolean hasDuplicatedLetter(String pitcherString) {
        Set<Character> pitchSet = new HashSet<>();
        char[] pitcherArray = pitcherString.toCharArray();

        for (Character eachPitch : pitcherArray) {
            pitchSet.add(eachPitch);
        }

        return pitchSet.size() != pitcherArray.length;
    }

    private static boolean hasZeroNumber(String pitcherString) {
        return pitcherString.contains("0");
    }
}
