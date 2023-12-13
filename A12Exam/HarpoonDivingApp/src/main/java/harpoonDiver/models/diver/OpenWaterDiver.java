package harpoonDiver.models.diver;

public class OpenWaterDiver extends BaseDiver {
    public static final double DEFAULT_OXYGEN = 30;

    public OpenWaterDiver(String name) {
        super(name, DEFAULT_OXYGEN);
    }
}
