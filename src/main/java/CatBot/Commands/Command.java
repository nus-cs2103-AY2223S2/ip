package CatBot.Commands;

import CatBot.CatBotException;
import CatBot.Storage.Storage;
import CatBot.TaskList.Task;
import CatBot.TaskList.TaskList;
import CatBot.Ui.Ui;

import java.util.ArrayList;

public abstract class Command {
    private boolean isExit = false;

    public boolean isExit() {
        return isExit;
    }

    protected void setExit() {
        isExit = true;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws CatBotException;

    public void loadCommand(ArrayList<Task> tasks) throws CatBotException {}
}
