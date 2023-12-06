package bank.entities.loan;

public class MortgageLoan extends BaseLoan {
    public static final int INTEREST_RATE = 3;
    public static final double AMOUNT = 50000;

    public MortgageLoan() {
        super(INTEREST_RATE, AMOUNT);
    }
}
