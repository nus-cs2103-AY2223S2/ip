package commands;

import exceptions.DukeException;
import storage.Storage;
import tasks.TaskList;
import views.UI;

public abstract class Command {
    String commandStatus;
    boolean isExit = false;

    public abstract void execute(UI ui, TaskList tasks, Storage storage) throws DukeException;

    public String getCommandStatus() {
        return this.commandStatus;
    };

    public boolean isExit() {
        return isExit;
    }
}
