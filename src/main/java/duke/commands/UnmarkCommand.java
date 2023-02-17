package duke.commands;

import duke.DukeException;
import duke.Parser;
import duke.TaskList;

/**
 * Command to unmark a task
 */
public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";

    /**
     * Constructor for command that unmarks a task
     *
     * @param input User's input
     */
    public UnmarkCommand(String input) {
        super(input);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks) throws DukeException {
        String input = getInput();
        try {
            // Prepare task index argument for unmarking task
            String[] tokens = input.split(" ");
            int taskIndex = Integer.parseInt(tokens[1]);

            String result = tasks.unmarkTask(taskIndex);
            return result;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException exception) {
            throw new DukeException(Parser.INVALID_COMMAND_EXCEPTION_MESSAGE);
        }
    }
}
