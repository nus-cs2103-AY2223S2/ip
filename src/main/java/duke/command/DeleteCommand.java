package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents Duke's delete function.
 */
public class DeleteCommand extends Command {
    /** Constructs the delete command. */
    public DeleteCommand() {}

    /**
     * Deletes a task and stores the resulting tasklist.
     *
     * @throws DukeException If user input is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage store) throws DukeException {
        Integer taskNum = ui.getTaskNum();
        String s = tasks.delete(taskNum);
        store.saveToFile(tasks);
        ui.printWithPartition("\tNoted. I've removed this task:\n\t  " + s + "\n" + "\tNow you have "
                + Integer.toString(tasks.size()) + " tasks in the list.\n");
    };
}
