package twofive.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import twofive.data.TaskList;
import twofive.storage.Storage;
import twofive.ui.Ui;

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
     * @param ui UI interacting with user.
     * @param storage Storage for saving or loading tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            LocalDate dueDate = LocalDate.parse(dueDateString, dueDateFormatter);
            ui.showMessage(tasks.getTasksOnDateList(dueDate));
        } catch (DateTimeParseException e) {
            ui.showError("Due date must be in the format yyyy-MM-dd, e.g. 2023-01-23");
        }
    }
}
