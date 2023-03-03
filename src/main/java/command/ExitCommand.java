package command;

import duke.Storage;
import duke.Ui;
import task.Tasklist;

/**
 * The ExitCommand class implements the Command interface and represents the command to exit the Duke application.
 */
public class ExitCommand implements Command {
    /**
     * Constructor that creates a new ExitCommand instance.
     */
    public ExitCommand() {
    }
    @Override
    public String execute(Ui ui, Tasklist list, Storage storage) {
        return ui.getExitReply();
    }

}
