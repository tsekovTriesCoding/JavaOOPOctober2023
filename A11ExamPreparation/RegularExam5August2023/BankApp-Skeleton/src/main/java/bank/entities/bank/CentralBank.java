package bank.entities.bank;

public class CentralBank extends BaseBank {
    public static final int DEFAULT_CAPACITY = 50;

    public CentralBank(String name) {
        super(name, DEFAULT_CAPACITY);
    }
}
