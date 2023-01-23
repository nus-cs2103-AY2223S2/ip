package sebastian.command;

import sebastian.main.Storage;
import sebastian.main.TaskList;
import sebastian.main.Ui;
import sebastian.sebastianExceptions.SebastianException;


abstract public class Command {
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws SebastianException;

    public boolean isExit() {
        return false;
    }
}
