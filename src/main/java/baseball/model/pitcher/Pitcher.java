package baseball.model.pitcher;

import baseball.view.Display;
import camp.nextstep.edu.missionutils.Console;

public class Pitcher {

    public int[] pitch() {
        return createdPitchNumbers();
    }

    private int[] createdPitchNumbers() {
        String pitcherString = receiveStringFromUser();
        validationCheck(pitcherString);

        return numericArrayPitcherNumbersOf(pitcherString);
    }

    private String receiveStringFromUser() {
        Display.printAskToUserMessage();
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
