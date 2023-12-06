package restaurant.entities.tables;

public class Indoors extends BaseTable {
    public static final double DEFAULT_PRICE_PER_PERSON = 3.5;

    public Indoors(int number, int size) {
        super(number, size, DEFAULT_PRICE_PER_PERSON);
    }
}
