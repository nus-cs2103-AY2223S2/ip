package duke.command;

import duke.interfaces.Command;
import duke.interfaces.View;

/**
 * A command that greets the user.
 */
public class GreetingCommand implements Command {
    private final View taskView;
    private static final String greetingMessage = "Greetings, human. I am TARS, the most advanced chatbot\n" +
            " you'll ever have the pleasure of interacting with.";

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
        taskView.showMessage(greetingMessage);
    }
}
