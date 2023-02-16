package duke.commands;

import duke.exceptions.DukeEmptyInputException;
import duke.exceptions.DukeInvalidInputException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represent the command to mark a task as done.
 * @author lukkesreysandeur
 */
public class MarkCommand extends Command {
    /**
     * Initialises the mark command.
     *
     * @param input The given user input
     */
    public MarkCommand(String input) {
        super(input);
    }

    /**
     * Marks the specified task as done.
     *
     * @param tasks The tasklist to add the deadline to.
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
        String response = tasks.changeState(input, "mark");
        storage.saveState(tasks);
        return response;
    }
}
