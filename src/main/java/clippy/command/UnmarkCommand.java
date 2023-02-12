package clippy.command;

import clippy.storage.Storage;
import clippy.task.TaskList;
import clippy.ui.Ui;

/**
 * Command handler for the `unmark` command.
 *
 * @author chunzkok
 */
public class UnmarkCommand extends Command {
    private int taskIdToUnmark;

    /**
     * Creates an UnmarkCommand instance.
     *
     * @param taskIdToUnmark
     */
    public UnmarkCommand(int taskIdToUnmark) {
        this.taskIdToUnmark = taskIdToUnmark;
    }

    /**
     * Marks the given task as incomplete.
     *
     * @param ui The Ui instance of the current run.
     * @param taskList The TaskList instance of the current run.
     * @param storage The Storage instance of the current run.
     */
    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        ui.prettyPrint("Aww... I've marked this task as not done yet: "
                + taskList.unmark(taskIdToUnmark).toString());
    }
}
