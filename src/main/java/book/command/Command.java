package book.command;

import book.Storage;
import book.TaskList;
import book.Ui;
import book.exception.BookException;

public abstract class Command {
    public abstract void execute(Storage storage, TaskList list, Ui ui) throws BookException;
    public boolean isExit() {
        return false;
    }
}
