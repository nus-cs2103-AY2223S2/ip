package cluck.commands;

import java.time.format.DateTimeParseException;

import cluck.messages.Messages;
import cluck.storage.Storage;
import cluck.tasklist.TaskList;
import cluck.tasks.Event;
import cluck.tasks.Task;

/**
 * EventCommand when executed successfully creates an event and adds it to the task list.
 * The command returns a success message, the added event,
 * and the new number of tasks in the task list.
 * If execution is unsuccessful, the command returns the error message as to why execution failed.
 */
public class EventCommand implements Command {
    private final String description;
    private final String startTime;
    private final String endTime;

    /**
     * Instantiates a new Event command.
     *
     * @param description the description
     * @param startTime   the start time
     * @param endTime     the end time
     */
    public EventCommand(String description, String startTime, String endTime) {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        try {
            Task event = new Event(description, startTime, endTime);
            taskList.addTask(event);
            storage.writetoSave(taskList);
            return Messages.MESSAGE_EVENT_ADDED + "\n" + event
                    + "\n" + String.format(Messages.MESSAGE_LIST_COUNT, taskList.taskCount());
        } catch (DateTimeParseException e) {
            return Messages.MESSAGE_DATE_INVALID;
        }
    }
}
