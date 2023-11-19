package A10DesignPatterns.Exercise.P05StrategyDesignPattern;

public class CreditCardPayment implements Payment {
    private String name;
    private String cardNumber;

    public CreditCardPayment(String nm, String ccNum) {
        this.name = nm;
        this.cardNumber = ccNum;
    }

    @Override
    public void pay(int amount) {
        System.out.println(amount + " paid with credit/debit card");
    }
}
