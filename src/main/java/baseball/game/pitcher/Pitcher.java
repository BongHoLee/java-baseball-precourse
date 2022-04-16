package baseball.game.pitcher;

import camp.nextstep.edu.missionutils.Console;

public class Pitcher {

    public int[] pitch() {
        return createdPitchNumbers();
    }

    private int[] createdPitchNumbers() {
        String pitcherString = receiveStringFromUser();

        if (isNotValid(pitcherString)) {
            throw new IllegalArgumentException("[" + pitcherString + "] is not valid");
        }

        return numericArrayPitcherNumbersOf(pitcherString);
    }

    private String receiveStringFromUser() {
        return Console.readLine();
    }

    private boolean isNotValid(String pitcherString) {
        return PitcherRule.isNotValid(pitcherString);
    }

    private int[] numericArrayPitcherNumbersOf(String pitcherString) {
        int[] pitcherNumbers = new int[3];
        char[] pitcherCharArray = pitcherString.toCharArray();
        for (int i=0; i<pitcherCharArray.length; i++) {
            pitcherNumbers[i] = Character.getNumericValue(pitcherCharArray[i]);
        }

        return pitcherNumbers;
    }
}
