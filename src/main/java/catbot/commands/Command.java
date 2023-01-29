package catbot.commands;

import catbot.CatBotException;
import catbot.storage.Storage;
import catbot.tasklist.Task;
import catbot.tasklist.TaskList;
import catbot.ui.Ui;

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
