package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Ui;

/**
 * Represents Duke's todo command
 */
public class AddToDoCommand extends Command {
    public AddToDoCommand() {}

    @Override
    public void execute(TaskList tasks, Ui ui, Storage store) throws DukeException {
        ToDo temp = new ToDo(ui.getName());
        tasks.add(temp);
        store.saveToFile(tasks);
        ui.printWithPartition("\tGot it. I've added this task:\n" + "\t  " + temp.toString()
                + "\n\tNow you have " + Integer.toString(tasks.size()) + " tasks in the list.\n");

    };

}
