package pix.commands;

import pix.data.MyData;
import pix.tasks.Event;
import pix.ui.Ui;

/**
 * EventCommand class to add event to list of tasks.
 */
public class EventCommand extends Command {
    /** Event object to be added. */
    protected Event event;

    /**
     * Constructs a new EventCommand.
     *
     * @param description Description of the event.
     * @param eventStart When the event starts.
     * @param eventEnd When the event ends.
     */
    public EventCommand(String description, String eventStart, String eventEnd) {
        this.event = new Event(description, eventStart, eventEnd);
    }

    /**
     * Executes the EventCommand and displays result.
     *
     * @param data Data storing the tasks.
     * @param ui Ui object which handles interaction with user.
     */
    public String execute(MyData data, Ui ui) {
        data.addTask(event);
        data.saveToFile();
        return ui.add(event.toString(), data.len());
    }
}

