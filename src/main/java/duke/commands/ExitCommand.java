package duke.commands;

import duke.exceptions.DukeEmptyInputException;
import duke.exceptions.DukeException;
import duke.exceptions.DukeInvalidInputException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents the command used to exit the program.
 * @author lukkesreysandeur
 */
public class ExitCommand extends Command {
    /**
     * Initialises the exit command.
     * @param input The given user input.
     */
    public ExitCommand(String input) {
        super(input);
    }

    /**
     * Checks if the syntax of the bye statement is correct, throws error if not.
     * @param tasks The tasklist to add the deadline to.
     * @param ui The ui object used to interact with the user.
     * @param storage The storage object that saves the current state of the tasklist.
     * @throws DukeInvalidInputException
     * @throws DukeEmptyInputException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeInvalidInputException {
        if (!input.equals("")) {
            DukeException e =  new DukeInvalidInputException("Say bye properly by typing only bye!");
            return e.toString();
        }
        return ui.sayBye();
    }

    /**
     * Returns true as this is the exit command.
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
