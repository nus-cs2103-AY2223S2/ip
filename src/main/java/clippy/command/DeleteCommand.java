package clippy.command;

import clippy.storage.Storage;
import clippy.task.TaskList;
import clippy.ui.Ui;

/**
 * Command handler for the `delete` command.
 *
 * @author chunzkok
 */
public class DeleteCommand extends Command {
    private int taskId;

    /**
     * Creates a DeleteCommand instance.
     *
     * @param taskId The ID of the task to be deleted.
     */
    public DeleteCommand(int taskId) {
        this.taskId = taskId;
    }

    /**
     * Deletes the specified task ID from TaskList.
     * @param ui The Ui instance of the current run.
     * @param taskList The TaskList instance of the current run.
     * @param storage The Storage instance of the current run.
     */
    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        ui.prettyPrint("Got it! I've removed this task:"
                + taskList.remove(taskId).toString());
    }
}
