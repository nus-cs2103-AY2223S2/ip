package hachi.commands;
import hachi.main.TaskList;
import hachi.main.Storage;
import hachi.main.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public boolean isExit(){
        return false;
    }
}