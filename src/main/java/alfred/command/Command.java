package alfred.command;

import alfred.exceptions.AlfredException;
import alfred.storage.Storage;
import alfred.task.TaskList;
import alfred.ui.Ui;

public abstract class Command {

    // Maybe can add storage.write() after every execute command?
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws AlfredException;
    public abstract boolean isExit();
}
