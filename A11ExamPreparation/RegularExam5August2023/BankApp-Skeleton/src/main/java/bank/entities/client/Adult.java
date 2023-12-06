package bank.entities.client;

public class Adult extends BaseClient {
    public static final int INITIAL_INTEREST = 4;
    public static final int DEFAULT_INTEREST_INCREASE_FACTOR = 2;

    public Adult(String name, String ID, double income) {
        super(name, ID, INITIAL_INTEREST, income);
    }

    @Override
    public void increase() {
        super.setInterest(super.getInterest() + DEFAULT_INTEREST_INCREASE_FACTOR);
    }
}
