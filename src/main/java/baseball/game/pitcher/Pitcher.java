package baseball.game.pitcher;

import camp.nextstep.edu.missionutils.Console;

public class Pitcher {

    public int pitch() {
        return createdPitchNumber();
    }

    private int createdPitchNumber() {
        String pitcherString = receiveStringFromUser();

        if (isNotValid(pitcherString)) {
            throw new IllegalArgumentException("[" + pitcherString + "] is not valid");
        }

        return Integer.parseInt(pitcherString);
    }

    private String receiveStringFromUser() {
        return Console.readLine();
    }

    private boolean isNotValid(String pitcherString) {
        return PitcherRule.isNotValid(pitcherString);
    }
}
