package twofive.command;

import twofive.data.TaskList;
import twofive.storage.Storage;
import twofive.task.Deadline;
import twofive.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private String taskDescription;
    private String deadlineString;

    public DeadlineCommand(String taskDescription, String deadlineString) {
        this.taskDescription = taskDescription;
        this.deadlineString = deadlineString;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            LocalDateTime deadline = LocalDateTime.parse(deadlineString, formatter);
            Deadline newDeadline = new Deadline(taskDescription, deadline);

            //Adds new task to list of tasks
            tasks.addTask(newDeadline);
            ui.showMesssage("Got it. I've added this task:\n " + newDeadline + "\n"
                    + "Now you have " + tasks.getTasksNum() + " tasks in the list");
        } catch (DateTimeParseException e) {
            ui.showError("Deadline must be in the format yyyy-MM-dd HH:mm, e.g. 2023-01-23 16:31");
        }
    }
}
