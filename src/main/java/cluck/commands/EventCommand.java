package cluck.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import cluck.messages.Messages;
import cluck.taskList.TaskList;
import cluck.tasks.Deadline;
import cluck.tasks.Event;
import cluck.tasks.Task;

public class EventCommand implements Command {
    private final String description;
    private final String startTime;
    private final String endTime;

    public EventCommand(String description, String startTime, String endTime) {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String execute(TaskList taskList) {
        try {
            Task event = new Event(description, startTime, endTime);
            taskList.addTask(event);
            return Messages.MESSAGE_EVENT_ADDED + "\n" + event
                    + String.format(Messages.MESSAGE_LIST_COUNT, taskList.taskCount());
        } catch (DateTimeParseException e) {
            return Messages.MESSAGE_DATE_INVALID;
        }
    }
}
