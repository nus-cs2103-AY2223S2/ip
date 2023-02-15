package duke.commands;

import duke.exceptions.DukeEmptyInputException;
import duke.exceptions.DukeInvalidInputException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents the command to mark a task as not done.
 * @author lukkesreysandeur
 */
public class UnmarkCommand extends Command {
    /**
     * Initialises the unmark command.
     *
     * @param input The given user input.
     */
    public UnmarkCommand(String input) {
        super(input);
    }

    /**
     * Marks the specified task as not done.
     *
     * @param tasks The tasklist to add the deadline to.
     * @param ui The ui object used to interact with the user.
     * @param storage The storage object that saves the current state of the tasklist.
     * @throws DukeInvalidInputException
     * @throws DukeEmptyInputException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeInvalidInputException, DukeEmptyInputException {
        String response = tasks.changeState(input, "unmark");
        storage.saveState(tasks);
        return response;
    }
}
