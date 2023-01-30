package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The UnmarkCommand class implements the action of unmarking tasks.
 *
 * @author Chia Jeremy
 */
public class UnmarkCommand extends Command {

    private final int index;

    /**
     * Class constructor for the unmark command.
     *
     * @param index the index of the task to unmark
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the unmark command.
     *
     * @param storage the file to save the tasks
     * @param tasks   the task lists
     * @param ui      the interface that deals with interactions with the user
     */
    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        tasks.unmark(this.index);
        storage.unmark(this.index);
        ui.setResponse("OK, I've marked this task as not done yet:\n" + tasks.getTask(this.index));
    }
}
