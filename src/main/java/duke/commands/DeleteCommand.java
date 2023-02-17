package duke.commands;

import duke.exceptions.DukeEmptyInputException;
import duke.exceptions.DukeInvalidInputException;
import duke.logic.Storage;
import duke.logic.TaskList;
import duke.logic.Ui;
/**
 * Represents the command to delete a certain task.
 * @author lukkesreysandeur
 */
public class DeleteCommand extends Command {
    /**
     * Initialises the delete command.
     *
     * @param input The given user input.
     */
    public DeleteCommand(String input) {
        super(input);
    }

    /**
     * Deletes the specified task.
     *
     * @param tasks The tasklist to delete from.
     * @param ui The ui object used to interact with the user.
     * @param storage The storage object that saves the current state of the tasklist.
     * @throws DukeInvalidInputException
     * @throws DukeEmptyInputException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeInvalidInputException, DukeEmptyInputException {
        if (input.equals("")) {
            throw new DukeEmptyInputException();
        }
        String response = tasks.delete(input);
        storage.saveState(tasks);
        return response;
    }
}
