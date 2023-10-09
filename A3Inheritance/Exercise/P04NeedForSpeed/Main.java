package A3Inheritance.Exercise.P04NeedForSpeed;

public class Main {
    public static void main(String[] args) {
        SportCar sc = new SportCar(100.20, 250);
        System.out.println(sc.getFuel());
        sc.drive(4);
        System.out.println(sc.getFuel());
        System.out.println(sc.getFuelConsumption());
    }
}
