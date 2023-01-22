package command;

import main.Storage;
import main.TaskList;
import main.Ui;
import sebastianExceptions.SebastianException;


abstract public class Command {
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws SebastianException;

    public boolean isExit() {
        return false;
    }
}
