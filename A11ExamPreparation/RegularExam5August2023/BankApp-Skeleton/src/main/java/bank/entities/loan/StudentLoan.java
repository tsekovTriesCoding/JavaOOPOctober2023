package bank.entities.loan;

public class StudentLoan extends BaseLoan {
    public static final int INTEREST_RATE = 1;
    public static final double AMOUNT = 10000;

    public StudentLoan() {
        super(INTEREST_RATE, AMOUNT);
    }
}
