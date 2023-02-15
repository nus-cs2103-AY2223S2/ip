package botanic.command;

import botanic.storage.Storage;
import botanic.task.TaskList;
import botanic.ui.Ui;

/**
 * Encapsulates the related fields and behavior
 * of the command to find tasks with names that matches
 * exactly with the given keyword.
 */
public class FindAllMatchCommand extends Command {
    private String keyword;

    /**
     * Instantiates FindCommand.
     *
     * @param keyword The keyword to search for in the list of tasks.
     */
    public FindAllMatchCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds (case-insensitive) the tasks with names that
     * partially or fully contain the given keyword.
     *
     * @param tasks The ArrayList of tasks.
     * @param storage The class that reads and write program data to hard drive.
     * @param ui The class that handles interaction with the users.
     * @return A string message to signify the success or failure of task executed.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        return tasks.findAllMatch(this.keyword);
    }
}
