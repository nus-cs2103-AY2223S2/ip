package duke.commands;

import java.time.LocalDateTime;

import duke.storage.Storage;
import duke.task.DeadlineTask;
import duke.task.Task;
import duke.task.TaskList;
import duke.uitext.UiText;

/**
 * Represents a command to add a deadline to a tasklist.
 */
public class AddDeadlineCommand extends Command {
    private String description;
    private LocalDateTime dateTime;

    /**
     * Creates a command to add a deadline.
     *
     * @param description The description of the task.
     * @param dateTime    The time of the deadline.
     */
    public AddDeadlineCommand(String description, LocalDateTime dateTime) {
        this.description = description;
        this.dateTime = dateTime;
    }

    @Override
    public String execute(TaskList taskList, UiText uiText, Storage storage) {
        Task task = new DeadlineTask(description, dateTime);
        taskList.add(task);
        return "Added: " + task;
    }
}
