package duke.commands;

import duke.backend.TaskList;
import duke.tasks.Event;
import duke.tasks.Task;

/**
 * Command to create an Event.
 */
public class MakeEvent extends Command {
    private String description;
    private String from;
    private String to;
    private TaskList tasklist;

    /**
     * Constructor for a command to make a new Event.
     * @param description Name of the Event.
     * @param from Start time of the Event.
     * @param to End time of the Event.
     * @param tasklist The list to add this Event to.
     */
    public MakeEvent(String description, String from, String to, TaskList tasklist) {
        this.description = description;
        this.from = from;
        this.to = to;
        this.tasklist = tasklist;
    }

    @Override
    public String execute() {
        Task t = new Event(description, from, to);
        tasklist.add(t);
        return "Added this new Event: \n" + t;
    }
}
