package command;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * Represents an executable command
 */
public abstract class Command {
    boolean isExit = false;
    public abstract void execute(TaskList taskList, Storage storage, Ui ui);
    public boolean isExit(){
        return isExit;
    };
}
