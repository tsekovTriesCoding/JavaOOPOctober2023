package bakery.entities.tables.classes;

public class OutsideTable extends BaseTable {
    public static final double DEFAULT_PRICE_PER_PERSON = 3.5;

    public OutsideTable(int tableNumber, int capacity) {
        super(tableNumber, capacity, DEFAULT_PRICE_PER_PERSON);
    }
}
