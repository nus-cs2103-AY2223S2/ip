package duke.command;

import duke.Ui;
import duke.TaskList;
import duke.Storage;

/**
 * UnknownCommand.
 */
public class UnknownCommand extends Command {
    /**
     * Constructor for UnknownCommand.
     */
    public UnknownCommand() {
    }

    /**
     * Executes printing of error.
     * @param tasks
     * @param ui
     * @param storage
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showError("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
