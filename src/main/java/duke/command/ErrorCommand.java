package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * ErrorCommand alerts to the user of an unknown command.
 */
public class ErrorCommand extends Command {

    /**
     * Creates an ErrorCommand.
     *
     * @param textCmd user input.
     */
    public ErrorCommand(String textCmd) {
        super(textCmd);
    }

    /**
     * Throws a DukeException to indicate to the user of an unknown command.
     *
     * @param taskList task list containing all existing tasks.
     * @param ui User interface for duke.
     * @param storage Storage information for tasks.
     * @throws  DukeException
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        throw new DukeException("I have no idea whats that command mate!\n");
    }
}
