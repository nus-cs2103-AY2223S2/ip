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
     * Abstract method to be implemented by {@code Command} representing the execution of the
     * {@code Command}.
     * @param storage {@code Storage} associated with the {@code Command}.
     * @param list {@code TaskList} associated with the {@code Command}.
     * @param ui {@code Ui} associated with the {@code Command}.
     * @throws BookException if an error occurs in the execution of a {@code Book}
     *     {@code Command}.
     */
    public abstract void execute(Storage storage, TaskList list, Ui ui) throws BookException;

    /**
     * Returns {@code true} if a {@code Command} is an {@code ExitCommand}, else returns
     * {@code false}.
     * @return {@code true} if a {@code Command} is an {@code ExitCommand}, else returns
     *     {@code false}.
     */
    public boolean isExit() {
        return false;
    }
}
