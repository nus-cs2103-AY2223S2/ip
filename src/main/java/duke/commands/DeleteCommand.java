package duke.commands;

import duke.DukeException;
import duke.Parser;
import duke.TaskList;

/**
 * Command to delete a task from the list
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";

    /**
     * Constructor for command that deletes a task
     *
     * @param input User's input
     */
    public DeleteCommand(String input) {
        super(input);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks) throws DukeException {
        String input = getInput();
        try {
            // Prepare argument for deleteTask
            String[] tokens = input.split(" ");
            int taskIndex = Integer.parseInt(tokens[1]);

            String result = tasks.deleteTask(taskIndex);
            return result;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException exception) {
            throw new DukeException(Parser.INVALID_COMMAND_EXCEPTION_MESSAGE);
        }
    }
}
