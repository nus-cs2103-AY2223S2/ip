package duke.command;

import duke.task.TaskList;
import duke.UI.UI;

public abstract class Command {

    public abstract void runCommand(TaskList task, UI ui);

    public abstract boolean isExit();
}
