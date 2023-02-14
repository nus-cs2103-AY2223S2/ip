package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a command that lists all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Returns the command as a String which is used to show to the user in the GUI.
     * Executes the command and lists all tasks in the task list.
     *
     * @param tl TaskList which the Duke will modify.
     * @param ui Ui to be used to facilitate interactions between user and the CLI.
     * @param storage Storage to be used to handle interactions with the save file.
     */
    @Override
    public String execute(TaskList tl, Ui ui, Storage storage) {
        String toShow = ui.showToUserList(tl);
        return toShow;
    }
}
