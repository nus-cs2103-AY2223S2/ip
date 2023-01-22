package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents an unknown command that Duke cannot execute
 */
public class UnknownCommand extends Command {
    public UnknownCommand() {}

    /**
     * An Unknown command.
     */
    public void execute(TaskList tasks, Ui ui, Storage store) throws DukeException {
        ui.throwAwayInput();
        throw new DukeException("I don't understand that command");
    };

}
