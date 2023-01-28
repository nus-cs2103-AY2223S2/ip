package commands;
import main.TaskList;
import main.Storage;
import main.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public boolean isExit(){
        return false;
    }
}