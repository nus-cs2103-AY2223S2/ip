package jarvis.commands;

import jarvis.backend.Parser;
import jarvis.backend.TaskList;
import jarvis.tasks.Event;
import jarvis.tasks.Task;

/**
 * Command to create an Event.
 */
public class MakeEvent extends Make {
    private String from;
    private String to;

    /**
     * Constructor for a command to make a new Event.
     * @param description Name of the Event.
     * @param from Start time of the Event.
     * @param to End time of the Event.
     * @param tasklist The list to add this Event to.
     */
    public MakeEvent(String description, String from, String to, TaskList tasklist, Parser parser) {
        super(description, tasklist, parser);
        this.from = from;
        this.to = to;
    }

    @Override
    public String execute() {
        Task t = new Event(description, from, to);
        //  Guard clause:
        Task duplicate = findDuplicates();
        if (duplicate != null) {
            return duplicateFound(t, duplicate);
        }
        tasklist.add(t);
        return "Added this new Event:\n" + t;
    }
}
