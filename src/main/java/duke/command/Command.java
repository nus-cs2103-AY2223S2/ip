package duke.command;

import duke.TaskList;

public abstract class Command {

    public abstract void execute(TaskList list);

    public boolean isExit() {
        return this instanceof ExitCommand;
    }

    @Override
    public String toString() {
        return getClass().getName();
    }
}
