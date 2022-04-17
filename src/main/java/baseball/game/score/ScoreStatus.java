package baseball.game.score;

public enum ScoreStatus {
    STRIKE("스트라이크"),
    BALL("볼");

    private String status;

    ScoreStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }
}
