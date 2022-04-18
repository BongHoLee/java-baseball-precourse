package baseball;

import baseball.controller.game.BaseballGame;
import baseball.controller.game.GameStatus;

public class Application {
    public static void main(String[] args) {
        BaseballGame baseballGame = new BaseballGame(GameStatus.NEW_GAME);
        baseballGame.start();
    }
}
