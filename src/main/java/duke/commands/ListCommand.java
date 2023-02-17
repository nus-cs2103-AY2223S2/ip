package duke.commands;

import duke.DukeException;
import duke.Parser;
import duke.TaskList;

/**
 * Command to display all tasks in the list
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    private static final String LIST_RESPONSE = "Current tasks in list:";

    /**
     * Constructor for command that lists current tasks
     *
     * @param input User's input
     */
    public ListCommand(String input) {
        super(input);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks) throws DukeException {
        String input = getInput();
        if (!input.equals(COMMAND_WORD)) {
            throw new DukeException(Parser.INVALID_COMMAND_EXCEPTION_MESSAGE);
        }
        return LIST_RESPONSE + tasks.toString();
    }
}
