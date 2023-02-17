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
    /**
     * Closes the window of the duke program.
     *
     * @param taskList The TaskList that will be modified or accessed.
     * @param inter The Ui that will interact with the user.
     * @param store The storage that will help store the task into the data file if TaskList is modified.
     * @return
     */
    public String execute(TaskList taskList, Ui inter, Storage store) {
        Platform.exit();
        return "";
    }

    public boolean isExit() {
        return true;
    }
}
