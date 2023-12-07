package goldDigger.models.discoverer;

public class Archaeologist extends BaseDiscoverer {
    public static final double DEFAULT_ENERGY = 60;

    public Archaeologist(String name) {
        super(name, DEFAULT_ENERGY);
    }
}
