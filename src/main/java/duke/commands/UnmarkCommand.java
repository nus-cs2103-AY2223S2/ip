package duke.commands;

import java.io.IOException;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * A class that handles removing marking from tasks.
 */
public class UnmarkCommand extends Command {

    private final int idx;

    /**
     * Constructor for unmark task.
     * @param idx index for task to be unmarked.
     */
    public UnmarkCommand(int idx) {
        this.idx = idx;
        assert idx >= 0;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showToUser("OK, I've marked this task as not done yet:");
        tasks.unmarkTask(idx - 1);
        try {
            storage.writeMarkingToFile(storage.getFilePath(), "0", idx - 1);
        } catch (IOException e) {
            throw new DukeException("Error overwriting");
        }
        ui.showToUser("    " + idx + "." + tasks.printTask(idx - 1));
    }
}
