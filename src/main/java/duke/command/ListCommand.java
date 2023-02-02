package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates the related fields and behavior of the command to list out all tasks.
 */
public class ListCommand extends Command {
    /**
     * Prints out all tasks in the list.
     *
     * @param tasks The ArrayList of tasks.
     * @param storage The class that reads and write program data to hard drive.
     * @param ui The class that handles interaction with the users.
     * @return A string representation of the list of all the tasks.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        return tasks.print();
    }

    /**
     * Returns whether the command requires the program to exit.
     *
     * @return False indicating that program should not exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
