package A10DesignPatterns.Exercise.P02FactoryDesignPattern;

public class WhiteCake extends Cake {
    public WhiteCake(double diameter, double price, int pieces) {
        super(diameter, price, pieces);
    }

    @Override
    public void prepare() {
        System.out.println("Preparing white cake...");
    }

    @Override
    public void bake() {
        System.out.println("Baking white cake...");
    }

    @Override
    public void box() {
        System.out.println("Boxing white cake...");
        System.out.println("Your white cake is ready!");
        System.out.printf("Your bill is %.2f", this.price * this.pieces);
    }
}
