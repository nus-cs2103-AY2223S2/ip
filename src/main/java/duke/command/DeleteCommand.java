package duke.command;

import duke.TaskList;
import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.ui.Ui;

public class DeleteCommand extends Command {

    protected int index;

    protected Task deleted;

    /**
     * Initialises the object
     *
     * @param index Index of the task in the list
     */
    public DeleteCommand(int index) {

        assert index >= 0;

        this.index = index;
    }

    /**
     * Executes the current command
     *
     * @param list    The task list
     * @param ui      The ui object
     * @param storage The storage object
     * @throws DukeException Throws DukeException of a specific massage
     */
    public String execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        Task task = list.delete(index);
        this.deleted = task;
        storage.write(list);
        return Ui.delete(task, list.getLength());
    }

    /**
     * Checks if this is exit command
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Gets the deleted task
     *
     * @return The deleted task
     */
    public Task getDeleted() {
        return this.deleted;
    }
}
