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
     * @param input The given user input.
     */
    public FindCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeInvalidInputException, DukeEmptyInputException {
        String response = tasks.find(input);
        ui.printResponse(response);
    }
}
