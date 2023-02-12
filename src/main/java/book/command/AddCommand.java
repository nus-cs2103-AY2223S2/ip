package book.command;

import book.Storage;
import book.TaskList;
import book.Ui;
import book.exception.SaveException;
import book.task.Task;

/**
 * Implementation of a {@code Command} for adding {@code Task} objects in {@code TaskList}.
 */
public class AddCommand extends Command {
    /** The {@code Task} to be added. */
    private Task task;

    /**
     * Initializes an {@code AddCommand} object.
     *
     * @param task {@code Task} to be added by the {@code AddCommand}.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds the associated {@code Task} to the {@code TaskList} and updates the {@code Storage},
     * returns the {@code String} to be displayed on the {@code Gui}.
     *
     * @param storage {@code Storage} associated with the {@code AddCommand}.
     * @param list {@code TaskList} associated with the {@code AddCommand}.
     * @param ui {@code Ui} associated with the {@code AddCommand}.
     * @return {@code String} to be displayed on the {@code Gui}.
     * @throws SaveException if an error occurs with updating the {@code Storage}.
     */
    @Override
    public String execute(Storage storage, TaskList list, Ui ui) throws SaveException {
        list.addTask(this.task);
        storage.save(list);
        return ui.showAdd(this.task) + "\n" + ui.showTaskListSize(list);
    }
}
