package henz.command;

import java.time.format.DateTimeParseException;
import java.util.Arrays;

import henz.henzexception.CommandException;
import henz.parser.DateTimeParser;
import henz.storage.Storage;
import henz.tasklist.TaskList;
import henz.tasks.Event;
import henz.tasks.Task;
import henz.ui.Ui;

/**
 * EventCommand extends from Command.
 */
public class EventCommand extends Command {

    /**
     * Constructor.
     * @param request the request from the user
     */
    public EventCommand(String request) {
        super(request);
    }

    /**
     * Creates the event task and store it into the task list.
     * @param tasks   the task list
     * @param ui      the ui instance
     * @param storage the storage instance
     * @return string
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String[] values = this.unwrap();
            Task task = new Event(values[0], values[1], values[2]);

            tasks.add(task);
            storage.save(tasks);

            String message = "Got it. I've added this task:\n"
                + task
                + "\nNow you have "
                + tasks.size()
                + " tasks in the list.\n";

            return message;
        } catch (CommandException error) {
            return error.getMessage();
        }

    }

    /**
     * Checks if the command has the inputs that it needs for the creation of task.
     * @throws CommandException
     */
    @Override
    public void checkCommandRequirement() throws CommandException {
        String[] values = super.getRequest().split(" ");
        String message = "";

        if (values.length <= 1) {
            message = "Description cannot be empty";
        }

        if (!super.getRequest().contains("/from")) {
            message = "Your request must include /from";
        }

        if (!super.getRequest().contains("/to")) {
            message = "Your request must include /to";
        }

        if (super.getRequest().indexOf("/from") > super.getRequest().indexOf("/to")) {
            message = "Your request must be in the following order: <Description> /from <Datetime> /to <Datetime>";
        }

        if (!message.isEmpty()) {
            throw new CommandException(message);
        }
    }

    /**
     * Unwraps the user's command into a string array that holds the following
     * information:
     * <ol>
     * <li>Description - the description of the task</li>
     * <li>From - the start datetime of the task</li>
     * <li>To - the end datetime of the task</li>
     * </ol>
     * @return a string array that stores the description, start datetime and end
     *         datetime of
     *         the task.
     * @throws CommandException
     */
    @Override
    public String[] unwrap() throws CommandException {
        String[] values = super.getRequest().split(" ");

        // Throws RequestExecution if there are any issues with the request
        checkCommandRequirement();

        try {
            int fromIndex = Arrays.asList(values).indexOf("/from");
            int toIndex = Arrays.asList(values).indexOf("/to");
            String description = String.join(" ", Arrays.copyOfRange(values, 1, fromIndex));
            String from = String.join(" ", Arrays.copyOfRange(values, fromIndex + 1, toIndex));
            from = DateTimeParser.parse(from);
            String to = String.join(" ", Arrays.copyOfRange(values, toIndex + 1, values.length));
            to = DateTimeParser.parse(to);

            return new String[] { description, from, to };
        } catch (DateTimeParseException e) {
            throw new CommandException("Datetime has to be in the following format: yyyy-mm-dd HHmm");
        }
    }
}
