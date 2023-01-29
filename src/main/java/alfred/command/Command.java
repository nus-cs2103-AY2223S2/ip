package alfred.command;

import alfred.task.TaskList;
import alfred.ui.Ui;
import alfred.storage.Storage;
import alfred.exceptions.AlfredException;

public abstract class Command {

    // Maybe can add storage.write() after every execute command?
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws AlfredException;
    public abstract boolean isExit();
}
