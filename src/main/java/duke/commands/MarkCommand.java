package duke.commands;

import java.io.IOException;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * A class that handles marking task.
 */
public class MarkCommand extends Command {

    private final int idx;

    /**
     * Constructor for mark command.
     * @param idx index of task to be marked.
     */
    public MarkCommand(int idx) {
        this.idx = idx;
        assert idx >= 0;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showToUser("Nice! I've marked this task as done:");
        tasks.markTask(idx - 1);
        try {
            storage.writeMarkingToFile(storage.getFilePath(), "1", idx - 1);
        } catch (IOException e) {
            throw new DukeException("Error overwriting");
        }
        ui.showToUser("    " + idx + "." + tasks.printTask(idx - 1));
    }
}
