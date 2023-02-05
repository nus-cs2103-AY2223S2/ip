package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.gui.Ui;

/**
 * ExitCommand - User enters the bye command.
 */
public class ExitCommand extends Command {

    boolean exit;

    /**
     * Public constructor
     * Initialize exit to be true.
     */
    public ExitCommand() {
        this.exit = true;
    }
    @Override
    public boolean isExit() {
        return this.exit;
    }

    /**
     * Print the exit message
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.byeMessage();
    }

}
