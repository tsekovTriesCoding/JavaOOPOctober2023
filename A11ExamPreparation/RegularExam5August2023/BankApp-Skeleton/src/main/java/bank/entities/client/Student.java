package bank.entities.client;

public class Student extends BaseClient {
    public static final int INITIAL_INTEREST = 2;
    public static final int DEFAULT_INTEREST_INCREASE_FACTOR = 1;

    public Student(String name, String ID, double income) {
        super(name, ID, INITIAL_INTEREST, income);
    }

    @Override
    public void increase() {
        super.setInterest(super.getInterest() + DEFAULT_INTEREST_INCREASE_FACTOR);
    }
}
