package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.DukeEmptyInputException;
import duke.exceptions.DukeInvalidInputException;

/**
 * Represents the command to find tasks with a specified keyword.
 * @author lukkesreysandeur
 */
public class FindCommand extends Command {
    /**
     * Initialises the find command.
     *
     * @param input The given user input.
     */
    public FindCommand(String input) {
        super(input);
    }

    /**
     * Finds all tasks with the keyword in their description.
     *
     * @param tasks The tasklist to search.
     * @param ui The ui object used to interact with the user.
     * @param storage The storage object that saves the current state of the tasklist.
     * @return
     * @throws DukeInvalidInputException
     * @throws DukeEmptyInputException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeInvalidInputException, DukeEmptyInputException {
        String response = tasks.find(input);
        return response;
    }
}
