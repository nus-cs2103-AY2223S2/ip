package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents Duke's deadline function
 */
public class AddDeadlineCommand extends Command {
    /** Constructs the add deadline command. */
    public AddDeadlineCommand() {}

    /**
     * Adds a Deadline task to the tasklist and stores the new tasklist.
     *
     * @throws DukeException If user input is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage store) throws DukeException {
        String[] data = ui.getDeadline();
        Deadline temp = new Deadline(data[0], data[1]);
        tasks.add(temp);
        store.saveToFile(tasks);
        ui.printWithPartition("\tGot it. I've added this task:\n" + "\t  " + temp.toString()
                + "\n\tNow you have " + Integer.toString(tasks.size()) + " tasks in the list.\n");

    };

}
