package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class InvalidCommand extends Command {
    public InvalidCommand() {
        super();
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Ui.showInvalidCommand();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        return o instanceof InvalidCommand;
    }
}
