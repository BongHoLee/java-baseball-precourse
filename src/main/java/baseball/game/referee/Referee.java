package baseball.game.referee;

public class Referee {
    private int strikeZone;

    public Referee() {
        this.strikeZone = StrikeZoneFactory.createStrikeZone();
    }

}
