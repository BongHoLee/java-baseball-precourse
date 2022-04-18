package baseball;

import baseball.controller.BaseballGame;
import baseball.status.GameStatus;

public class Application {
    public static void main(String[] args) {
        BaseballGame baseballGame = new BaseballGame(GameStatus.NEW_GAME);
        baseballGame.start();
    }
}
