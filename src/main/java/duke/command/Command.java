package duke.command;

import duke.*;

public abstract class Command {
    protected String userInput;
    protected boolean isExit;

    public Command(String userInput) {
        this.userInput = userInput;
        this.isExit = false;
    }

    public abstract void execute(TaskList tasks, Ui ui);

    public boolean getExitStatus() {
        return isExit;
    }
}
