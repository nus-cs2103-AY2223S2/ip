package book.command;

import book.Storage;
import book.TaskList;
import book.Ui;
import book.exception.SaveException;
import book.task.Task;

/**
 * Implementation of a {@code Command} for deleting {@code Task} objects in {@code TaskList}.
 */
public class DeleteCommand extends Command {
    /** The {@code int} 0-based index of the {@code Task} to be deleted. */
    private int index;

    /**
     * Initializes an {@code DeleteCommand} object.
     *
     * @param index {@code int} 1-based index of the {@code Task} to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index - 1;
    }

    /**
     * Deletes the {@code Task} at the associated {@code int} index in the {@code TaskList} and
     * updates the {@code Storage}, returns the {@code String} to be displayed on the {@code Gui}.
     *
     * @param storage {@code Storage} associated with the {@code Command}.
     * @param list {@code TaskList} associated with the {@code Command}.
     * @param ui {@code Ui} associated with the {@code Command}.
     * @return {@code String} to be displayed on the {@code Gui}.
     * @throws SaveException if an error occurs with updating the {@code Storage}.
     */
    @Override
    public String execute(Storage storage, TaskList list, Ui ui)
            throws SaveException, IndexOutOfBoundsException {
        Task task = list.deleteTask(this.index);
        storage.save(list);
        return ui.showDelete(task) + "\n" + ui.showTaskListSize(list);
    }
}
