package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {
    public void execute(TaskList taskList, Ui inter, Storage store) {
        inter.list(taskList);
    }

    public boolean isExit() {
        return false;
    }
}
