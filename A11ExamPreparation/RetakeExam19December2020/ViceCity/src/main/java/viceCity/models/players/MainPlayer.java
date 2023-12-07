package viceCity.models.players;

public class MainPlayer extends BasePlayer {
    public static final String DEFAULT_NAME = "Tommy Vercetti";
    public static final int DEFAULT_LIFE_POINTS = 100;

    public MainPlayer() {
        super(DEFAULT_NAME, DEFAULT_LIFE_POINTS);
    }
}
