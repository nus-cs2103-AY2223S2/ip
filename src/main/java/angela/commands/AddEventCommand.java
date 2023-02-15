package angela.commands;

import java.time.LocalDateTime;

import angela.storage.Storage;
import angela.task.EventTask;
import angela.task.Task;
import angela.task.TaskList;
import angela.uitext.UiText;

/**
 * Represents a command to add an event to a tasklist.
 */
public class AddEventCommand extends Command {
    private String description;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    /**
     * Creates a new event command.
     * @param description The description of the event.
     * @param startDateTime The start time of the event.
     * @param endDateTime The end time of the event.
     */
    public AddEventCommand(String description, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.description = description;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    @Override
    public String execute(TaskList taskList, UiText uiText, Storage storage) {
        Task task = new EventTask(description, startDateTime, endDateTime);
        taskList.add(task);
        return "Added: " + task;
    }
}
