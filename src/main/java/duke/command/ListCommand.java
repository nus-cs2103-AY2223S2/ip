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
     *
     * @param tasks TaskList that contains all the current tasks
     * @param ui Ui that communicates with the user
     * @param storage Storage that backups the saving of tasks
     * @return string reply to be shown to user after executing this command
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.size() <= 0) {
            return "Seems like you don't have any tasks yet!";
        }
        return tasks.print();
    }
}
