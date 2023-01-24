package command;

import interfaces.Command;
import interfaces.View;

public class GreetingCommand implements Command {
    private final View taskView;
    private static final String greetingMessage = "Greetings, human. I am TARS, the most advanced chatbot" +
            " you'll ever have the pleasure of interacting with.";
    GreetingCommand(View taskView) {
        this.taskView = taskView;
    }

    @Override
    public void execute() {
        taskView.showMessage(greetingMessage);
    }
}
