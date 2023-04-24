package twofive.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import twofive.data.TaskList;
import twofive.storage.Storage;
import twofive.ui.TaskContainer;

/**
 * Lists all tasks with a deadline, start time or end time, given a
 * date when command is executed.
 */
public class DueDateCommand extends Command {
    private static DateTimeFormatter dueDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private String dueDateString;

    public DueDateCommand(String dueDateString) {
        this.dueDateString = dueDateString;
    }

    /**
     * Prints out all tasks added given a task list with a deadline, start time
     * or end time on the same day as the date provided.
     *
     * @param tasks List of all current tasks.
     * @param storage Storage for saving or loading tasks.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        String commandResult = "";
        try {
            LocalDate dueDate = LocalDate.parse(dueDateString, dueDateFormatter);
            TaskContainer.setTasks(tasks.getTasksOnDate(dueDate));
            commandResult = "Here is a list of tasks due on "
                    + dueDate.format(DateTimeFormatter.ofPattern("EEE MMM d yyyy"));
        } catch (DateTimeParseException e) {
            commandResult = "Due date must be in the format yyyy-MM-dd, e.g. 2023-01-23";
        }
        return commandResult;
    }
}
