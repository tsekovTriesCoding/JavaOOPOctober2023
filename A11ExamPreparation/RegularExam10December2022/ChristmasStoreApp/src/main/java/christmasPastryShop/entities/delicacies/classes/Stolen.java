package christmasPastryShop.entities.delicacies.classes;

public class Stolen extends BaseDelicacy {
    public static final double INITIAL_PORTION = 250;

    public Stolen(String name, double price) {
        super(name, INITIAL_PORTION, price);
    }
}
