package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.UI.UI;

public abstract class Command {

    public abstract void runCommand(TaskList task, UI ui, Storage storage);

    public abstract boolean isExit();
}
