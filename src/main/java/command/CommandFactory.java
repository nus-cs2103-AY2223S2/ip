package command;

import interfaces.Command;
import interfaces.Model;
import interfaces.View;

public class CommandFactory {
    private final Model taskModel;
    private final View taskView;

    public CommandFactory(Model taskModel, View taskView) {
        this.taskModel = taskModel;
        this.taskView = taskView;
    }

    public Command createCommand(String input) {
        if (input.equalsIgnoreCase("greet")) {
            return new GreetingCommand(taskView);
        } else if (input.equalsIgnoreCase("bye")){
            return new ByeCommand(taskView);
        } else {
            return new EchoCommand(taskView, input);
        }
    }
}
