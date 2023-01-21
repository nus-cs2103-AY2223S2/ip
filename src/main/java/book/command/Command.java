package book.command;

import book.exception.BookException;

import book.Storage;
import book.TaskList;
import book.Ui;

public abstract class Command {
    public abstract void execute(Storage storage, TaskList list, Ui ui) throws BookException;
    public boolean isExit() {
        return false;
    }
}
