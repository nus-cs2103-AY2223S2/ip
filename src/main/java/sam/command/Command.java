package sam.command;

import sam.SamException;
import sam.Ui;
import sam.storage.Storage;
import sam.task.TaskList;

public abstract class Command {
    protected String args;

    public Command(String args) {
        this.args = args;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage)
            throws SamException;
}
