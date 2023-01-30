package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents the command to list out all tasks in task list.
 */
public class ListCommand extends Command {

    public ListCommand() {
        super();
    }

    /**
     * Prints out the tasks in the list to the user via the ui.
     * @param tasks TaskList that contains all the current tasks.
     * @param ui Ui that communicates with the user.
     * @param storage Storage that backups the saving of tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessageWithoutNewline(tasks.print());
    }
}
