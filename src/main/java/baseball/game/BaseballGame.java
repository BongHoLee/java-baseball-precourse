package baseball.game;

import baseball.game.pitcher.Pitcher;
import baseball.game.referee.Referee;
import baseball.game.score.ScoreBoard;
import baseball.game.score.ScoreStatus;
import camp.nextstep.edu.missionutils.Console;
import java.util.Map;

public class BaseballGame {

    private final static String ILLEGAL_CHOICE_ERROR_MESSAGE = "잘못된 입력입니다. 게임을 종료합니다.";
    private final static String GAME_CLEAR_MESSAGE = "3개의 숫자를 모두 맞히셨습니다! 게임 종료\n"
            + "게임을 새로 시작하려면 1, 종료하려면 2를 입력해주세요";

    private final static String PLAY_NEXT = "1";
    private final static String GAME_STOP = "2";

    private Referee referee;
    private GameStatus gameStatus;

    public BaseballGame(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public void start() {
        while (gameStatus != GameStatus.STOP) {
            playGame();
        }
    }

    private void playGame() {
        refereeEnter();
        Map<ScoreStatus, Integer> scoreStatusMap = referee.playBall();
        processResult(scoreStatusMap);
    }

    private void refereeEnter() {
        if (gameStatus == GameStatus.NEW_GAME) {
            referee = new Referee(new Pitcher());
        }
    }

    private void processResult(Map<ScoreStatus, Integer> scoreStatusMap) {
        ScoreBoard.display(scoreStatusMap);
        processForGameClear(scoreStatusMap);
        processForExistsGame(scoreStatusMap);

    }

    private void processForGameClear(Map<ScoreStatus, Integer> scoreStatusMap) {
        if (scoreStatusMap.get(ScoreStatus.STRIKE) == 3) {
            displayGameClearMessage();
            askToNextGame();
        }
    }

    private void displayGameClearMessage() {
        System.out.println(GAME_CLEAR_MESSAGE);
    }

    private void askToNextGame() {
        String userChoice = Console.readLine();
        processForUserChoice(userChoice);
    }

    private void processForUserChoice(String userChoice) {
        if (!(PLAY_NEXT.equals(userChoice)) && !(GAME_STOP.equals(userChoice))) {
            throw new IllegalArgumentException(ILLEGAL_CHOICE_ERROR_MESSAGE);
        }
        gameStopIf(userChoice);
        playNextGameIf(userChoice);
    }

    private void gameStopIf(String userChoice) {
        if (GAME_STOP.equals(userChoice)) {
            gameStatus = GameStatus.STOP;
        }
    }

    private void playNextGameIf(String userChoice) {
        if (PLAY_NEXT.equals(userChoice)) {
            gameStatus = GameStatus.NEW_GAME;
        }
    }

    private void processForExistsGame(Map<ScoreStatus, Integer> scoreStatusMap) {
        if (scoreStatusMap.get(ScoreStatus.STRIKE) != 3) {
            gameStatus = GameStatus.EXISTING_GAME;
        }
    }
}
