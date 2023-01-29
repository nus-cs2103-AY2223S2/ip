package lulu.command;

import lulu.TaskList;
import lulu.UI;
import lulu.Storage;
public abstract class Command {
    public abstract void execute(TaskList tasks, UI ui, Storage storage);

    public boolean isExit() {
        return false;
    }
}
