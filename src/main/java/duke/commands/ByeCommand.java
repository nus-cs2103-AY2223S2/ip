package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import javafx.application.Platform;

/**
 * Command to be executed when the Duke program is exited.
 *
 * @author Cheam Jia Wei
 */
public class ByeCommand extends Command {
    public String execute(TaskList taskList, Ui inter, Storage store) {
        Platform.exit();
        return "";
    }

    public boolean isExit() {
        return true;
    }
}
