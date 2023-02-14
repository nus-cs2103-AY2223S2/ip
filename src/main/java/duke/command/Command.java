package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command is an abstract class that encapsulates all Commands for the Chatbot.
 */
public abstract class Command {
    protected boolean isExit;
    protected String textCmd;

    public Command(String textCmd) {
        this.textCmd = textCmd;
        this.isExit = false;
    }

    public abstract String execute(Ui ui, Storage storage, TaskList taskList) throws DukeException;
    public boolean isExit() {
        return isExit;
    }
}
