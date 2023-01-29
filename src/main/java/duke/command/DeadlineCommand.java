package command;

import java.util.Arrays;

import dukeexception.CommandException;
import parser.DateTimeParser;
import storage.Storage;
import tasklist.TaskList;
import tasks.Deadline;
import tasks.Task;
import ui.Ui;

/**
 * DeadlineCommand class extends from Command class.
 */
public class DeadlineCommand extends Command {

    /**
     * Constructor.
     * @param request the request from the user
     */
    public DeadlineCommand(String request) {
        super(request);
    }

    /**
     * Creates the deadline task and store it into the task list.
     * @param tasks   the task list
     * @param ui      the ui instance
     * @param storage the storage instance
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String[] values = this.unwrap();
            Task task = new Deadline(values[0], values[1]);

            tasks.add(task);
            ui.showTaskAdded(task, tasks.size());

            storage.save(tasks);
        } catch (CommandException error) {
            ui.showError(error);
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

        if (!super.getRequest().contains("/by")) {
            message = "Your request must include /by";
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
     * <li>By - the end datetime of the task</li>
     * </ol>
     * @return a string array that stores the description and the end datetime of
     *         the task.
     * @throws CommandException
     */
    @Override
    public String[] unwrap() throws CommandException {
        String[] values = super.getRequest().split(" ");

        // Throws RequestExecution if there are any issues with the request
        checkCommandRequirement();

        int byIndex = Arrays.asList(values).indexOf("/by");
        String description = String.join(" ", Arrays.copyOfRange(values, 1, byIndex));
        String by = String.join(" ", Arrays.copyOfRange(values, byIndex + 1, values.length));
        by = DateTimeParser.parse(by);

        return new String[] { description, by };
    }
}
