package A10DesignPatterns.Lab.P03CommandPattern;

import java.util.ArrayList;
import java.util.List;

public class ModifyPrice {
    private final List<Command> commands;
    private Command command;

    public ModifyPrice() {
        this.commands = new ArrayList<>();
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public void invoke() {
        this.commands.add(this.command);
        System.out.print(this.command.executeAction());
    }
}
