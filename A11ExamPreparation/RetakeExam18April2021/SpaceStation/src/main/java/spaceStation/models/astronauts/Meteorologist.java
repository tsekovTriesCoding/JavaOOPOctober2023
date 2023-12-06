package spaceStation.models.astronauts;

public class Meteorologist extends BaseAstronaut {
    public static final double DEFAULT_OXYGEN = 90;

    public Meteorologist(String name) {
        super(name, DEFAULT_OXYGEN);
    }

}
