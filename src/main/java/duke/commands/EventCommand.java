package duke.commands;

import duke.DukeException;
import duke.Parser;
import duke.TaskList;
import duke.tasks.EventTask;

/**
 * Command to create an Event task
 */
public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    private static final String PERIOD_BEGIN_PREFIX = "/from ";
    private static final String PERIOD_END_PREFIX = "/to ";

    /**
     * Constructor for command that creates an Event task
     *
     * @param input User's input
     */
    public EventCommand(String input) {
        super(input);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks) throws DukeException {
        String input = getInput();
        try {
            // Prepare arguments for EventTask
            int firstPrefixIndex = input.indexOf(PERIOD_BEGIN_PREFIX);
            int secondPrefixIndex = input.indexOf(PERIOD_END_PREFIX);

            // Check that prefixes exist in input
            if (firstPrefixIndex == -1 || secondPrefixIndex == -1) {
                throw new IndexOutOfBoundsException();
            }

            String taskName = input.substring(COMMAND_WORD.length(), firstPrefixIndex - 1);
            String startDateString = input.substring(firstPrefixIndex + PERIOD_BEGIN_PREFIX.length(),
                    secondPrefixIndex - 1);
            String endDateString = input.substring(secondPrefixIndex + PERIOD_END_PREFIX.length());

            String result = tasks.addTask(EventTask.createTask(taskName, startDateString, endDateString));
            return result;
        } catch (IndexOutOfBoundsException exception) {
            throw new DukeException(Parser.INVALID_COMMAND_EXCEPTION_MESSAGE);
        }
    }
}
