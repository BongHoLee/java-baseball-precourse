package baseball.model.pitcher;

import java.util.HashSet;
import java.util.Set;

public class PitcherRule {

    private final static String NOT_VALID_ERROR_MESSAGE = "유효하지 않은 값 입니다. 게임을 종료합니다.";

    public static void validationCheck(String pitcherString) {
        if (isNotThreeLetter(pitcherString) ||
                isNotNumber(pitcherString) ||
                hasDuplicatedLetter(pitcherString) ||
                hasZeroNumber(pitcherString)) {
            throw new IllegalArgumentException(NOT_VALID_ERROR_MESSAGE);
        }

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
