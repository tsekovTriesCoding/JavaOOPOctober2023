package A10DesignPatterns.Exercise.P04CommandDesignPattern;

public class OnCommand implements Command {
    private Receiver receiver;

    public OnCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.switchOn();
    }
}
