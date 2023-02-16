package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.IoHandler;

/**
 * Represents Duke's todo command
 */
public class AddToDoCommand extends Command {
    public AddToDoCommand() {}

    /**
     * Adds a ToDo task to the tasklist and stores the new tasklist.
     *
     * @throws DukeException If user input is invalid.
     */
    @Override
    public String execute(TaskList tasks, IoHandler ui, Storage store) throws DukeException {
        ToDo temp = new ToDo(ui.getName());
        tasks.add(temp);
        store.saveToFile(tasks);
        return ui.produceDukeOutput("Got it. I've added this task:\n" + "\t" + temp.toString()
                + "\nNow you have " + Integer.toString(tasks.size()) + " tasks in the list.\n");
    };

}
