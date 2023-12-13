package harpoonDiver.models.diver;

public class WreckDiver extends BaseDiver {
    public static final double DEFAULT_OXYGEN = 150;

    public WreckDiver(String name) {
        super(name, DEFAULT_OXYGEN);
    }
}
