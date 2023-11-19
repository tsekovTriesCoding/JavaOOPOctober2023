package A10DesignPatterns.Exercise.P02FactoryDesignPattern;

public class BiscuitCake extends Cake {
    public BiscuitCake(double diameter, double price, int pieces) {
        super(diameter, price, pieces);
    }

    @Override
    public void prepare() {
        System.out.println("Preparing biscuit cake...");
    }

    @Override
    public void bake() {
        System.out.println("Baking biscuit cake...");
    }

    @Override
    public void box() {
        System.out.println("Boxing biscuit cake...");
        System.out.println("Your biscuit cake is ready!");
        System.out.printf("Your bill is %.2f", this.price * this.pieces);
    }
}
