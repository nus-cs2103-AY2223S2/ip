package crystal.command;

import crystal.CrystalException;
import crystal.TaskList;
import crystal.Ui;
import crystal.Storage;
public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui,Storage storage) throws CrystalException;


    public abstract boolean isExit();
}

