package A10DesignPatterns.Exercise.P04CommandDesignPattern;

public class Dvd implements Receiver {
    @Override
    public void switchOn() {
        System.out.println("Switch on from DVDPlayer");
    }
}
