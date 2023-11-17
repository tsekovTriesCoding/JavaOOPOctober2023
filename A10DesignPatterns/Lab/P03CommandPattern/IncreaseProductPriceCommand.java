package A10DesignPatterns.Lab.P03CommandPattern;

public class IncreaseProductPriceCommand implements Command {
    private final Product product;
    private final int amount;

    public IncreaseProductPriceCommand(Product product, int amount) {
        this.product = product;
        this.amount = amount;
    }

    @Override
    public String executeAction() {
        this.product.increasePrice(this.amount);
        return String.format("The price for the %s product has been increased by %d$.%n", this.product.getName(), this.amount);
    }
}
