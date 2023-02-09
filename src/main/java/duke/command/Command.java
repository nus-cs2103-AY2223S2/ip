package duke.command;

import duke.TaskList;
import duke.exception.DukeException;

public abstract class Command {
    protected String userInput;
    protected boolean isExit;

    public Command(String userInput) {
        this.userInput = userInput;
        this.isExit = false;
    }

    public abstract String execute(TaskList tasks) throws DukeException;

    public boolean getExitStatus() {
        return isExit;
    }
}
