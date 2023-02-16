package duke.commands;

import duke.logic.Storage;
import duke.logic.TaskList;
import duke.logic.Ui;
import duke.exceptions.DukeEmptyInputException;
import duke.exceptions.DukeInvalidInputException;

/**
 * Represents the command to help users understand other commands.
 * @author lukkesreysandeur
 */
public class HelpCommand extends Command {
    /**
     * Initialses the help command.
     *
     * @param input The given user input.
     */
    public HelpCommand(String input) {
        super(input);
    }

    /**
     * Shows the user the list of commands and how to use them.
     *
     * @param tasks The tasklist used by Duke.
     * @param ui The ui object that is used to interact with the user
     * @param storage The storage object used by Duke.
     * @return A list of all commands with their uses.
     * @throws DukeInvalidInputException
     * @throws DukeEmptyInputException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeInvalidInputException, DukeEmptyInputException {
        if (!input.equals("")) {
            throw new DukeInvalidInputException("Help should not have anything behind it! Why don't you try again?");
        }
        return "Hi! Here's the list of commands and their use case:\n\n1. todo: Adds a task that needs to be done." +
                "\n\t(e.g. todo homework)\n2. deadline: Adds a task that has a deadline." +
                "\n\t(e.g. deadline assignment /by 2024-05-21)\n3. event: Adds a task that as a start and end time." +
                "\n\t(e.g. event birthday /from 0000 /to 2359)\n4. mark/unmark: Mark a task as done/not done" +
                "\n\t(e.g. mark 1, unmark 3)\n5. delete: Delete a task. (e.g. delete 2)\n6. help: Displays all commands." +
                "\n7. list: Lists all tasks currently in the todo list.\n8. bye: Exits the program." ;
    }
}
