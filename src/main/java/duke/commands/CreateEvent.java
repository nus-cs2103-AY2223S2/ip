package duke.commands;

import duke.storage.Storage;
import duke.tasks.Event;
import duke.tasks.TaskList;
import duke.ui.UserInterface;

/**
 * Creates a new event.
 *
 * @author Samarth Verma
 */
public class CreateEvent extends Command {

    private String description;
    private String from;
    private String to;

    /**
     * Creates a new CreateEvent command.
     *
     * @param description The description of the event.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     */
    public CreateEvent(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList list, UserInterface ui, Storage storage) throws Exception {
        Event event = new Event(description, from, to);
        list.add(event);
        ui.showMessage("Got it. I've added this task: " + event);

        storage.save(list);
    }
}
