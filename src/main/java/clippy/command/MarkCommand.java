package clippy.command;

import clippy.storage.Storage;
import clippy.task.TaskList;
import clippy.ui.Ui;

/**
 * Command handler for the `mark` command.
 *
 * @author chunzkok
 */
public class MarkCommand extends Command {
    private int taskIdToMark;

    /**
     * Creates a MarkCommand instance.
     *
     * @param taskIdToMark The ID of the task to be marked as completed.
     */
    public MarkCommand(int taskIdToMark) {
        this.taskIdToMark = taskIdToMark;
    }

    /**
     * Marks the given task as completed.
     *
     * @param ui The Ui instance of the current run.
     * @param taskList The TaskList instance of the current run.
     * @param storage The Storage instance of the current run.
     */
    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        ui.prettyPrint("Great job! I've marked this task as done: "
                + taskList.mark(taskIdToMark).toString());
    }
}
