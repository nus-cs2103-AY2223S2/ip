package duke.commands;

import duke.*;

abstract public class Command {
    public abstract void execute(TaskList taskList, Ui inter, Storage store);

    public abstract boolean isExit();
}
