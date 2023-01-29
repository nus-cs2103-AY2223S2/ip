package duke.command;

import duke.exception.DukeException;

import duke.tasklist.TaskList;

public abstract class Command {

    private final boolean isExit;

    protected Command(boolean isExit) {
        this.isExit = isExit;
    }

    public boolean isExitCommand() {
        return isExit;
    }

    public abstract void execute(TaskList taskList) throws DukeException;

}
