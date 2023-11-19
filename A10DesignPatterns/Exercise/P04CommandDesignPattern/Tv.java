package A10DesignPatterns.Exercise.P04CommandDesignPattern;

public class Tv implements Receiver {

    @Override
    public void switchOn() {
        System.out.println("Switch on from TV");
    }
}
