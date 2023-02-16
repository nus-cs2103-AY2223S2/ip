package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Event;
import duke.task.TaskList;
import duke.ui.IoHandler;

/**
 * Represents Duke's Event function.
 */
public class AddEventCommand extends Command {
    /** Constructs the add event command. */
    public AddEventCommand() {}

    /**
     * Adds an Event task to the tasklist and stores the new tasklist.
     *
     * @throws DukeException If user input is invalid.
     */
    @Override
    public String execute(TaskList tasks, IoHandler ui, Storage store) throws DukeException {
        String[] data = ui.getEvent();
        Event temp = new Event(data[0], data[1], data[2]);
        tasks.add(temp);
        store.saveToFile(tasks);
        return ui.produceDukeOutput("Got it. I've added this task:\n" + "\t" + temp.toString()
                + "\nNow you have " + Integer.toString(tasks.size()) + " tasks in the list.\n");
    };
}
