package chattime.command;

import chattime.storage.Storage;
import chattime.task.TaskList;
import chattime.ui.Ui;

public abstract class Command {

    public Command() {}

    public abstract void execute(Ui ui, TaskList taskList, Storage storage);

}
