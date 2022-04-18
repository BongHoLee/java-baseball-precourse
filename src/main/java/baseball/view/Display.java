package baseball.view;

import baseball.status.ScoreStatus;
import java.util.Map;

public class Display {

    private final static String GAME_CLEAR_MESSAGE = "3개의 숫자를 모두 맞히셨습니다! 게임 종료\n"
            + "게임을 새로 시작하려면 1, 종료하려면 2를 입력해주세요";

    private final static String ASK_TO_USER_MESSAGE = "숫자를 입력해 주세요 : ";

    public static void printScore(Map<ScoreStatus, Integer> scoreStatusMap) {
        System.out.println(ScoreRender.render(scoreStatusMap));
    }

    public static void printGameClearMessage() {
        System.out.println(GAME_CLEAR_MESSAGE);
    }

    public static void printAskToUserMessage() {
        System.out.println(ASK_TO_USER_MESSAGE);
    }
}
