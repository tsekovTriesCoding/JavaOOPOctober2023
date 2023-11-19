package A10DesignPatterns.Exercise.P02FactoryDesignPattern;

public class SpinachCake extends Cake {
    public SpinachCake(double diameter, double price, int pieces) {
        super(diameter, price, pieces);
    }

    @Override
    public void prepare() {
        System.out.println("Preparing spinach cake...");
    }

    @Override
    public void bake() {
        System.out.println("Baking spinach cake...");
    }

    @Override
    public void box() {
        System.out.println("Boxing spinach cake...");
        System.out.println("Your spinach cake is ready!");
        System.out.printf("Your bill is %.2f", this.price * this.pieces);
    }
}
