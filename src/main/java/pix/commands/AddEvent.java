package pix.commands;

import pix.data.MyData;
import pix.tasks.Event;
import pix.ui.Ui;

/**
 * AddEvent class to add event to list of tasks.
 */
public class AddEvent extends Command {
    /** Event object to be added. */
    protected Event event;

    /**
     * Constructs a new AddEvent command.
     *
     * @param description Description of the event.
     * @param from When the event starts.
     * @param to When the event ends.
     */
    public AddEvent(String description, String from, String to) {
        this.event = new Event(description, from, to);
    }

    /**
     * Executes the AddEvent command and displays result.
     *
     * @param data Data storing the tasks.
     * @param ui Ui object which handles interaction with user.
     */
    public String execute(MyData data, Ui ui) {
        data.setData(event);
        data.saveToFile();
        return ui.add(event.toString(), data.len());
    }
}

