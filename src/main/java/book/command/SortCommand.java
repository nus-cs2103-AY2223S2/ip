package book.command;

import book.Storage;
import book.TaskList;
import book.Ui;
import book.exception.SaveException;

/**
 * Implementation of a {@code Command} for sorting {@code Task} objects in {@code TaskList}.
 */
public class SortCommand extends Command {
    /**
     * Sorts the {@code Task}s in the {@code TaskList} and updates the {@code Storage},
     * returns the {@code String} to be displayed on the {@code Gui}.
     *
     * @param storage {@code Storage} associated with the {@code Command}.
     * @param list {@code TaskList} associated with the {@code Command}.
     * @param ui {@code Ui} associated with the {@code Command}.
     * @return
     */
    @Override
    public String execute(Storage storage, TaskList list, Ui ui) throws SaveException {
        list.sortTaskList();
        storage.save(list);
        return ui.showSortedList(list) + "\n" + ui.showTaskListSize(list);
    }
}
