package duke.commands;

import duke.exceptions.DukeEmptyInputException;
import duke.exceptions.DukeException;
import duke.exceptions.DukeInvalidInputException;
import duke.logic.Storage;
import duke.logic.TaskList;
import duke.logic.Ui;

/**
 * Represents the command to list all items in the tasklist.
 * @author lukkesreysandeur
 */
public class ListCommand extends Command {
    /**
     * Initialises the list command.
     *
     * @param input The given user input.
     */
    public ListCommand(String input) {
        super(input);
    }

    /**
     * Lists all items currently in the tasklist.
     *
     * @param tasks The tasklist to add the deadline to.
     * @param ui The ui object used to interact with the user.
     * @param storage The storage object that saves the current state of the tasklist.
     * @throws DukeInvalidInputException
     * @throws DukeEmptyInputException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeInvalidInputException {
        if (!input.equals("")) {
            DukeException e =  new DukeInvalidInputException("If you would like to see the items in the list, " +
                    "please type just list");
            return e.toString();
        }
        String response = tasks.listItems();
        return response;
    }
}
