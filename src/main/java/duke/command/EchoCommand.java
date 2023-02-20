package duke.command;

import duke.interfaces.Command;
import duke.interfaces.View;

/**
 * A simple command to echo what the user enters.
 */
public class EchoCommand implements Command {
    private final View taskView;
    private final String echoString;

    /**
     * Instantiates a command that echoes the supplied string to the view upon execution.
     * @param taskView The current view.
     * @param echoString The string to echo.
     */
    EchoCommand(View taskView, String echoString) {
        this.echoString = echoString;
        this.taskView = taskView;
    }

    /**
     * Echoes the provided echoString to the view.
     */
    @Override
    public void execute() {
        taskView.showMessage("You said: " + echoString);
    }
}
