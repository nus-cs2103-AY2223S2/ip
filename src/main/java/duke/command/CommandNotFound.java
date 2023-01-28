package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * CommandNotFound - when user enters a Command and Duke
 *         does not understand the command.
 */
public class CommandNotFound extends Command {

    /**
     * Public constructor
     */
    public CommandNotFound() {

    }

    /**
     * Display the UI
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showCommandNotFound();
    }
}
