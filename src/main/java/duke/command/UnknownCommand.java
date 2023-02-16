package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.IoHandler;

/**
 * Represents an unknown command that Duke cannot execute
 */
public class UnknownCommand extends Command {

    /** Constructs an unknown command. */
    public UnknownCommand() {}

    /**
     * Throws a DukeException indicating that the command is not understood.
     *
     * @throws DukeException Always as command is not understood.
     */
    public String execute(TaskList tasks, IoHandler ui, Storage store) throws DukeException {
        ui.throwAwayInput();
        throw new DukeException("I don't understand that command");
    };

}
