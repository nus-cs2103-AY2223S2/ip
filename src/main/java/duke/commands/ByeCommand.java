package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ByeCommand extends Command {
    public void execute(TaskList taskList, Ui inter, Storage store) {
        inter.exit();
    }

    public boolean isExit() {
        return true;
    }
}
