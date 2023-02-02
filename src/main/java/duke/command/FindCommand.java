package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates the related fields and behavior of the command to find tasks with a keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Instantiates FindCommand.
     *
     * @param keyword The keyword to search for in the list of tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds the tasks that contain the given keyword.
     *
     * @param tasks The ArrayList of tasks.
     * @param storage The class that reads and write program data to hard drive.
     * @param ui The class that handles interaction with the users.
     * @return A string message to signify the success or failure of task executed.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        return tasks.find(this.keyword);
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
