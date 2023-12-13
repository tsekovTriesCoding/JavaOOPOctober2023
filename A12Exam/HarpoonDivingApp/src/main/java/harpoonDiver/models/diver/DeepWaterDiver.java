package harpoonDiver.models.diver;

public class DeepWaterDiver extends BaseDiver {
    public static final double DEFAULT_OXYGEN = 90;

    public DeepWaterDiver(String name) {
        super(name, DEFAULT_OXYGEN);
    }
}
