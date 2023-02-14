package duke.commands;

import java.time.LocalDateTime;

import duke.storage.Storage;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.TaskList;
import duke.uitext.UiText;

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
