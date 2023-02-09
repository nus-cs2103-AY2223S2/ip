package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The MarkCommand class implements the action of marking tasks.
 *
 * @author Chia Jeremy
 */
public class MarkCommand extends Command {

    private final int index;

    /**
     * Class constructor for the mark command.
     *
     * @param index the index of the task to mark
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the mark command.
     *
     * @param storage the file to save the tasks
     * @param tasks   the task lists
     * @param ui      the interface that deals with interactions with the user
     */
    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        markTaskList(tasks);
        markStorage(storage);
        setUiResponse(tasks, ui);
    }

    private void markTaskList(TaskList tasks) {
        tasks.mark(this.index);
    }

    private void markStorage(Storage storage) {
        storage.mark(this.index);
    }

    private void setUiResponse(TaskList tasks, Ui ui) {
        ui.setResponse("Nice! I've marked this task as done:\n" + tasks.getTask(this.index));
    }
}
