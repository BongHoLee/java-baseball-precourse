package baseball;

import baseball.game.BaseballGame;
import baseball.game.GameStatus;

public class Application {
    public static void main(String[] args) {
        BaseballGame baseballGame = new BaseballGame(GameStatus.NEW_GAME);
        baseballGame.start();
    }
}
