package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.byeMessage();
    }

}
