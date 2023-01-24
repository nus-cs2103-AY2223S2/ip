package commands;

import enums.CommandType;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

public abstract class Command {
    public abstract void execute(TaskList tl, Ui ui, Storage s);
    public abstract boolean isExit();

}
