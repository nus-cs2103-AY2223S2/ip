package duke.command;

import duke.task.Event;
import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents an EventCommand that implements the interface Command.
 */
public class EventCommand implements Command {
    private String eventDescription;
    private String[] eventPeriod;

    /**
     * A constructor for EventCommand.
     * @param eventDescription The description of the Event task.
     * @param eventPeriod A String array that contains the event period.
     */
    public EventCommand(String eventDescription, String[] eventPeriod) {
        this.eventDescription = eventDescription;
        this.eventPeriod = eventPeriod;
    }
    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        Task newTask = new Event(eventDescription, eventPeriod);
        tasks.addTask(newTask);
        String toDisplay = String.format("Gotcha! I have added this task:\n%s\nNow you have %d tasks in the list.",
                newTask, tasks.getSize());
        ui.displayMessage(toDisplay);
        return toDisplay;
    }
}
