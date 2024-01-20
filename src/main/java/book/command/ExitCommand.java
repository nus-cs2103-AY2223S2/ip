package book.command;

import book.Storage;
import book.TaskList;
import book.Ui;
import book.exception.SaveException;

/**
 * Implementation of a {@code Command} for exiting {@code Book}.
 */
public class ExitCommand extends Command {
    /**
     * Updates the {@code Storage}, returns the {@code String} to be displayed on the {@code Gui}.
     *
     * @param storage {@code Storage} associated with the {@code Command}.
     * @param list {@code TaskList} associated with the {@code Command}.
     * @param ui {@code Ui} associated with the {@code Command}.
     * @return {@code String} to be displayed on the {@code Gui}.
     * @throws SaveException if an error occurs with updating the {@code Storage}.
     */
    @Override
    public String execute(Storage storage, TaskList list, Ui ui) throws SaveException {
        storage.save(list);
        return ui.showExit();
    }

    /**
     * Returns {@code true}.
     *
     * @return {@code true}.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
