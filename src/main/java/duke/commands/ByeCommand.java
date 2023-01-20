package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command to be executed when the Duke program is exited.
 *
 * @author Cheam Jia Wei
 */
public class ByeCommand extends Command {
    public void execute(TaskList taskList, Ui inter, Storage store) {
        inter.exit();
    }

    public boolean isExit() {
        return true;
    }
}
