package crystal.command;

import crystal.TaskList;
import crystal.Ui;
import crystal.Storage;
public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui,Storage storage);


    public abstract boolean isExit();
}

