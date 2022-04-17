package baseball.game.pitcher;

import camp.nextstep.edu.missionutils.Console;

public class Pitcher {
    private final static String ASK_TO_USER_MESSAGE = "숫자를 입력해 주세요 : ";

    public int[] pitch() {
        return createdPitchNumbers();
    }

    private int[] createdPitchNumbers() {
        String pitcherString = receiveStringFromUser();
        validationCheck(pitcherString);

        return numericArrayPitcherNumbersOf(pitcherString);
    }

    private String receiveStringFromUser() {
        System.out.print(ASK_TO_USER_MESSAGE);
        return Console.readLine();
    }

    private void validationCheck(String pitcherString) {
        PitcherRule.validationCheck(pitcherString);
    }

    private int[] numericArrayPitcherNumbersOf(String pitcherString) {
        int[] pitcherNumbers = new int[3];
        char[] pitcherCharArray = pitcherString.toCharArray();
        for (int i = 0; i < pitcherCharArray.length; i++) {
            pitcherNumbers[i] = Character.getNumericValue(pitcherCharArray[i]);
        }

        return pitcherNumbers;
    }
}
