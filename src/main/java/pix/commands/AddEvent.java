package duke.commands;

import duke.data.MyData;
import duke.tasks.Event;
import duke.ui.Ui;

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

    public void execute(MyData data, Ui ui) {
        data.setData(event);
        data.saveToFile();
        ui.add(event.toString(), data.len());
    }
}

