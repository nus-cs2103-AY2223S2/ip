package book.command;

import book.Storage;
import book.TaskList;
import book.Ui;
import book.exception.BookException;

/**
 * Abstract class representing a {@code Command}.
 */
public abstract class Command {
    /**
     * Executes the {@code Command}, returns the {@code String} to be displayed on the {@code Gui}.
     *
     * @param storage {@code Storage} associated with the {@code Command}.
     * @param list {@code TaskList} associated with the {@code Command}.
     * @param ui {@code Ui} associated with the {@code Command}.
     * @return {@code String} to be displayed on the {@code Gui}.
     * @throws BookException if an error occurs in the execution of a {@code Book}
     *         {@code Command}.
     */
    public abstract String execute(Storage storage, TaskList list, Ui ui) throws BookException;

    /**
     * Returns {@code true} if a {@code Command} is an {@code ExitCommand}, else returns
     * {@code false}.
     *
     * @return {@code true} if a {@code Command} is an {@code ExitCommand}, else returns
     *         {@code false}.
     */
    public boolean isExit() {
        return false;
    }
}
