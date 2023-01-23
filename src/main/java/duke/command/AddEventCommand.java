package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Event;
import duke.task.TaskList;
import duke.ui.Ui;

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
    public void execute(TaskList tasks, Ui ui, Storage store) throws DukeException {
        String[] data = ui.getEvent();
        Event temp = new Event(data[0], data[1], data[2]);
        tasks.add(temp);
        store.saveToFile(tasks);
        ui.printWithPartition("\tGot it. I've added this task:\n" + "\t  " + temp.toString()
                + "\n\tNow you have " + Integer.toString(tasks.size()) + " tasks in the list.\n");
    };
}
