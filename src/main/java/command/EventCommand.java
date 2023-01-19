package command;

import dukeexeption.InvalidArgumentException;
import storage.TaskList;
import task.Event;

import java.time.LocalDate;

public class EventCommand extends Command {
    private String task;
    private LocalDate startTime;
    private LocalDate endTime;

    /**
     * Constructor for a create event command.
     * @param task      task to be added
     * @param startTime start time for task
     * @param endTime   end time for task
     */
    public EventCommand(String task, LocalDate startTime, LocalDate endTime) {
        this.task = task;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String run(TaskList taskList) throws InvalidArgumentException {
        if (this.endTime.isBefore(this.startTime)) {
            throw new InvalidArgumentException("End date must not be earlier than start date.");
        }
        Event newEvent = taskList.createEvent(this.task, this.startTime, this.endTime);
        return "Got it. I've added this task:\n" + newEvent +
                "\nNow you have " + taskList.countTask() + " tasks in the list.";
    }
}
