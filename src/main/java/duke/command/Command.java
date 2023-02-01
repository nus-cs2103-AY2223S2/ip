package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

public abstract class Command {
    protected String userInput;
    protected boolean isExit;

    public Command(String userInput) {
        this.userInput = userInput;
        this.isExit = false;
    }

    public abstract void execute(TaskList tasks, Ui ui) throws DukeException;

    public boolean getExitStatus() {
        return isExit;
    }
}
