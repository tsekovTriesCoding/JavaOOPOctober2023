package A10DesignPatterns.Exercise.P01SingletonDesignPattern;

public class Hashcode {
    private String point;

    private static Hashcode instance;

    private Hashcode(String point) {
        this.point = point;
    }

    public static Hashcode getInstance(String point) {
        if (instance == null) {
            instance = new Hashcode(point);
        }

        return instance;
    }

    public String getPoint() {
        return point;
    }
}
