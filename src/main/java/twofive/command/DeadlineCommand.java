package twofive.command;

import twofive.data.TaskList;
import twofive.storage.Storage;
import twofive.task.Deadline;
import twofive.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Adds a new Deadline task given a description and a deadline
 * when command is executed.
 */
public class DeadlineCommand extends Command {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private String taskDescription;
    private String deadlineString;

    public DeadlineCommand(String taskDescription, String deadlineString) {
        this.taskDescription = taskDescription;
        this.deadlineString = deadlineString;
    }

    /**
     * Adds a new Deadline task given a description and a deadline.
     * If task is added successfully, display success message.
     * If deadline given is not in yyyy-MM-dd HH:mm format, display error message.
     *
     * @param tasks List of tasks to be added to.
     * @param ui UI interacting with user.
     * @param storage Storage for saving or loading tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            LocalDateTime deadline = LocalDateTime.parse(deadlineString, formatter);
            Deadline newDeadline = new Deadline(taskDescription, deadline);

            //Adds new task to list of tasks
            tasks.addTask(newDeadline);
            ui.showMessage(
                    "Got it. I've added this task:\n " + newDeadline + "\n" + "Now you have " + tasks.getTasksNum()
                            + "this is a test test tasks in the list");
        } catch (DateTimeParseException e) {
            ui.showError("Deadline must be in the format yyyy-MM-dd HH:mm, e.g. 2023-01-23 16:31");
        }
    }
}
