package A10DesignPatterns.Lab.P02Facade;

public class Main {
    public static void main(String[] args) {
        Car car = new CarBuilderFacade()
                .info()
                .withType("BMW")
                .withColor("Red")
                .withNumberOfDoors(4)
                .built()
                .inCity("Dortmund")
                .atAddress("Address street 34")
                .build();

        System.out.println(car);
    }
}
