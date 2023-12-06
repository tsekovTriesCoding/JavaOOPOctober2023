package football.entities.supplement;

public class Powdered extends BaseSupplement {
    public static final int DEFAULT_ENERGY = 120;
    public static final double DEFAULT_PRICE = 15;

    public Powdered() {
        super(DEFAULT_ENERGY, DEFAULT_PRICE);
    }
}
