package duke.commands;

import duke.DukeException;
import duke.Parser;
import duke.TaskList;

/**
 * Command to mark a task as completed
 */
public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";

    /**
     * Constructor for command that marks a task
     *
     * @param input User's input
     */
    public MarkCommand(String input) {
        super(input);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks) throws DukeException {
        String input = getInput();
        try {
            // Prepare arguments for marking task
            String[] tokens = input.split(" ");
            int taskIndex = Integer.parseInt(tokens[1]);

            String result = tasks.markTask(taskIndex);
            return result;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException exception) {
            throw new DukeException(Parser.INVALID_COMMAND_EXCEPTION_MESSAGE);
        }
    }
}
