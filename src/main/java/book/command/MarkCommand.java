package book.command;

import book.Storage;
import book.TaskList;
import book.Ui;
import book.exception.SaveException;
import book.task.Task;

/**
 * Implementation of a {@code Command} for marking a {@code Task} object in {@code TaskList}.
 */
public class MarkCommand extends Command {
    /** The {@code int} 0-based index of the {@code Task} to be marked. */
    private int index;

    /**
     * Initializes a {@code MarkCommand} object.
     *
     * @param index {@code int} 1-based index of the {@code Task} to be marked.
     */
    public MarkCommand(int index) {
        this.index = index - 1;
    }

    /**
     * Marks the {@code Task} at the associated {@code int} index in the {@code TaskList} as
     * complete and updates the {@code Storage}, returns the {@code String} to be displayed on the
     * {@code Gui}.
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
        Task task = list.get(index);
        task.mark();
        storage.save(list);
        return ui.showMark(task);
    }
}
