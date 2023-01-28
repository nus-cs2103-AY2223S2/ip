package iris.command;

import iris.TaskList;
import iris.exception.IrisException;
import iris.Ui;
import iris.TaskStore;

public abstract class Command {
    public boolean isEnd() {
        return false;
    }
    public abstract void execute(TaskList tasks, Ui ui, TaskStore taskStore) throws IrisException;

    @Override
    public boolean equals(Object o) {
        return o instanceof Command;
    }
}
