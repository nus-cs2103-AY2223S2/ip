package duke.command;

import duke.interfaces.Command;
import duke.interfaces.View;
public class GreetingCommand implements Command {
    private static final String GREETING_MESSAGE = "Greetings, human. I am TARS, the most advanced chatbot\n"
            + " you'll ever have the pleasure of interacting with.";
    private final View taskView;
    GreetingCommand(View taskView) {
        this.taskView = taskView;
    }

    @Override
    public void execute() {
        taskView.showMessage(GREETING_MESSAGE);
    }
}
