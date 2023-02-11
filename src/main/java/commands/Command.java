package commands;

import exception.TreeBotException;
import interfaces.IUndoable;
import tasks.TaskList;
import utils.Storage;

import java.util.Deque;


public abstract class Command {

    protected TaskList taskList;
    protected Storage storage;
    protected Deque<IUndoable> history;


    public abstract String execute();


    abstract String toResultString();

    public Command injectContext(TaskList taskList, Storage storage, Deque<IUndoable> history) {
        this.taskList = taskList;
        this.storage = storage;
        this.history = history;
        return this;
    }

    public boolean isContextExists() {
        return this.history != null && this.taskList != null
                && this.storage != null;
    }
}
