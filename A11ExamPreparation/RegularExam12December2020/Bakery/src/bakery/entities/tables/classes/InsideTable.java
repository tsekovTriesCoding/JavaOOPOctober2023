package bakery.entities.tables.classes;

public class InsideTable extends BaseTable {
    public static final double DEFAULT_PRICE_PER_PERSON = 2.5;

    public InsideTable(int tableNumber, int capacity) {
        super(tableNumber, capacity, DEFAULT_PRICE_PER_PERSON);
    }
}
