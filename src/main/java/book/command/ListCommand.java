package book.command;

import book.Storage;
import book.TaskList;
import book.Ui;

/**
 * Implementation of a {@code Command} for listing {@code Task} objects in {@code TaskList}.
 */
public class ListCommand extends Command {
    /**
     * Lists the {@code Task} objects in the associated {@code TaskList}, returns the
     * {@code String} to be displayed on the {@code Gui}.
     *
     * @param storage {@code Storage} associated with the {@code Command}.
     * @param list {@code TaskList} associated with the {@code Command}.
     * @param ui {@code Ui} associated with the {@code Command}.
     * @return {@code String} to be displayed on the {@code Gui}.
     */
    @Override
    public String execute(Storage storage, TaskList list, Ui ui) {
        return ui.showList(list) + ui.showTaskListSize(list);
    }
}
