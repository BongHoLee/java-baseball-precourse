package baseball.controller;

import baseball.model.pitcher.Pitcher;
import baseball.model.referee.Referee;
import baseball.status.GameStatus;
import baseball.view.Display;
import baseball.status.ScoreStatus;
import camp.nextstep.edu.missionutils.Console;
import java.util.Map;

public class BaseballGame {

    private final static String ILLEGAL_CHOICE_ERROR_MESSAGE = "잘못된 입력입니다. 게임을 종료합니다.";

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
        Display.printScore(scoreStatusMap);
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
        Display.printGameClearMessage();
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
