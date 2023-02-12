package duke.command;

import duke.interfaces.Command;
import duke.interfaces.View;

/**
 * A command that greets the user.
 */
public class GreetingCommand implements Command {
    private static final String GREETING_MESSAGE = "Greetings, human. I am TARS, the most advanced chatbot\n"
            + " you'll ever have the pleasure of interacting with.";
    private final View taskView;

    /**
     * Instantiates a command that displays a greeting message to the view upon execution.
     * @param taskView The current view.
     */
    GreetingCommand(View taskView) {
        this.taskView = taskView;
    }

    /**
     * Display greeting message to view.
     */
    @Override
    public void execute() {
        taskView.showMessage(GREETING_MESSAGE);
    }
}
