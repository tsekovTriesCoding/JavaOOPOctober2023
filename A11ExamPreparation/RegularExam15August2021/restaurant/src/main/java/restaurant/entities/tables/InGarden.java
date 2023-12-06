package restaurant.entities.tables;

public class InGarden extends BaseTable {
    public static final double DEFAULT_PRICE_PER_PERSON = 4.5;

    public InGarden(int number, int size) {
        super(number, size, DEFAULT_PRICE_PER_PERSON);
    }
}
