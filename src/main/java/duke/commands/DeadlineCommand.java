package duke.commands;

import duke.DukeException;
import duke.Parser;
import duke.TaskList;
import duke.tasks.DeadlineTask;

/**
 * Command to create a Deadline task
 */
public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    private static final String DEADLINE_PREFIX = "/by ";

    /**
     * Constructor for command that creates a Deadline task
     *
     * @param input User's input
     */
    public DeadlineCommand(String input) {
        super(input);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks) throws DukeException {
        String input = getInput();

        try {
            // Prepare arguments for DeadlineTask
            int prefixIndex = input.indexOf(DEADLINE_PREFIX);
            String taskName = input.substring(COMMAND_WORD.length(), prefixIndex - 1);
            String deadline = input.substring(prefixIndex + DEADLINE_PREFIX.length());

            String result = tasks.addTask(DeadlineTask.createTask(taskName, deadline));
            return result;
        } catch (IndexOutOfBoundsException exception) {
            throw new DukeException(Parser.INVALID_COMMAND_EXCEPTION_MESSAGE);
        }
    }
}
