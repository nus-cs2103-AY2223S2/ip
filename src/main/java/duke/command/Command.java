package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.task.Ui;

public abstract class Command {
    private final String fullCommand;
    public Command(String command) {
        fullCommand = command;
    }

    public String getFullCommand() {
        return fullCommand;
    }

    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    public boolean isExit() {
        return false;
    }
}
