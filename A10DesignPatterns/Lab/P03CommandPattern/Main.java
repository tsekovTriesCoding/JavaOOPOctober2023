package A10DesignPatterns.Lab.P03CommandPattern;

public class Main {
    public static void main(String[] args) {
        ModifyPrice modifyPrice = new ModifyPrice();
        Product product = new Product("Chocolate",20);

        execute(modifyPrice, new IncreaseProductPriceCommand(product, 20));
        execute(modifyPrice, new IncreaseProductPriceCommand(product, 15));
        execute(modifyPrice, new DecreaseProductPriceCommand(product, 40));

        System.out.println(product);
    }

    private static void execute(ModifyPrice modifyPrice, Command productCommand) {
        modifyPrice.setCommand(productCommand);
        modifyPrice.invoke();
    }
}
