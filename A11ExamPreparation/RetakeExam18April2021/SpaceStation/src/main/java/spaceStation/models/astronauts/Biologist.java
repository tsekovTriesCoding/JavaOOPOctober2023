package spaceStation.models.astronauts;

public class Biologist extends BaseAstronaut {
    public static final double DEFAULT_OXYGEN = 70;

    public Biologist(String name) {
        super(name, DEFAULT_OXYGEN);
    }

    @Override
    public void breath() {
        super.setOxygen(Math.max(super.getOxygen() - 5, 0));
    }
}
