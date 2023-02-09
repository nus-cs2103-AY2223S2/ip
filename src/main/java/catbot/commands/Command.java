package catbot.commands;

import java.util.ArrayList;

import catbot.CatBotException;
import catbot.storage.Storage;
import catbot.tasklist.Task;
import catbot.tasklist.TaskList;
import catbot.ui.Ui;

/**
 * Parent class for all commands.
 */
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
