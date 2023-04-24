package twofive.command;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import twofive.data.TaskList;
import twofive.storage.Storage;
import twofive.task.Deadline;
import twofive.ui.TaskContainer;

/**
 * Adds a new Deadline task given a description and a deadline
 * when command is executed.
 */
public class DeadlineCommand extends Command {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private String taskDescription;
    private String deadlineString;

    /**
     * Represents a constructor for the DeadlineCommand class.
     *
     * @param taskDescription Description of the Deadline task.
     * @param deadlineString Deadline of the Deadline task.
     */
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
     * @param storage Storage for saving or loading tasks.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        String commandResult = "";
        try {
            LocalDateTime deadline = LocalDateTime.parse(deadlineString, formatter);
            Deadline newDeadline = new Deadline(taskDescription, deadline);

            //Adds new task to list of tasks
            tasks.addTask(newDeadline);
            storage.save(tasks);
            TaskContainer.setTasks(tasks.getTasks());
            commandResult = "TwoFive has added this task for you:\n " + newDeadline + "\n" + "Now you have "
                    + tasks.getTasksNum() + " tasks in the list";
        } catch (DateTimeParseException e) {
            commandResult = "Deadline must be in the format yyyy-MM-dd HH:mm, e.g. 2023-01-23 16:31";
        } catch (IOException e) {
            commandResult = e.getMessage();
        }
        return commandResult;
    }
}
