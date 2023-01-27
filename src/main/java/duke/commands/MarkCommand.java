package duke.commands;

import duke.Parser;
import duke.TaskList;
import duke.DukeException;

/**
 * Command to mark a task as completed
 */
public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";

    public MarkCommand(String input) {
        super(input);
    }
    @Override
    public String execute(TaskList tasks) throws DukeException {
        String input = super.input;
        try {
            String[] tokens = input.split(" ");
            int taskIndex = Integer.parseInt(tokens[1]);
            String result = tasks.markTask(taskIndex);
            return result;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException exception) {
            throw new DukeException(Parser.INVALID_COMMAND_EXCEPTION_MESSAGE);
        }
    }
}
