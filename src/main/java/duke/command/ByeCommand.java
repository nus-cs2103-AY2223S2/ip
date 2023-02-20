package duke.command;

import duke.interfaces.Command;
import duke.interfaces.CommandEventListener;
import duke.interfaces.View;

/**
 * A command that causes Duke to exit when executed.
 */
public class ByeCommand implements Command {
    private static final String EXIT_MESSAGE = "See you on the other side, human. Don't forget to bring a spacesuit!";
    private final View taskView;
    private final CommandEventListener exitEventListener;
    /**
     * Instantiates a command that causes Duke to exit when executed.
     * @param taskView The current view.
     */
    ByeCommand(View taskView, CommandEventListener exitEventListener) {
        this.taskView = taskView;
        this.exitEventListener = exitEventListener;
    }

    /**
     * Triggers duke to exit upon execution.
     */
    @Override
    public void execute() {
        taskView.showMessage(EXIT_MESSAGE);
        exitEventListener.onCommandEvent("bye");
    }
}
