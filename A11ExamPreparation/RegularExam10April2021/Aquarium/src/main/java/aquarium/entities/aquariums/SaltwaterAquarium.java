package aquarium.entities.aquariums;

public class SaltwaterAquarium extends BaseAquarium {
    public static final int DEFAULT_CAPACITY = 25;

    public SaltwaterAquarium(String name) {
        super(name, DEFAULT_CAPACITY);
    }
}
