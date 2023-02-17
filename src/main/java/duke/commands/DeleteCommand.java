package duke.commands;

import java.io.IOException;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * A class that handles deletion command.
 */
public class DeleteCommand extends Command {

    private final int idx;

    /**
     * Constructor for delete command.
     * @param idx the index of task to be deleted.
     */
    public DeleteCommand(int idx) {
        this.idx = idx;
        assert idx >= 0;
    }

    /**
     * Deletes task from TaskList
     *
     * @param tasks The TaskList to add the new task.
     * @param ui Ui given by Duke.
     * @param storage Storage for storing the newly created task.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showToUser("Noted. I've removed this task:");
        ui.showToUser("    " + idx + "." + tasks.printTask(idx - 1));
        tasks.deleteTask(idx - 1);
        try {
            storage.deleteTaskInFile(storage.getFilePath(), idx - 1);
        } catch (IOException e) {
            throw new DukeException("Error deleting");
        }
        ui.showToUser("Now you have " + tasks.getSize() + " tasks in the list.");
    }
}
