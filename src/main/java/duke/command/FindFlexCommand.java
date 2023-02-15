package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates the related fields and behavior
 * of a command to find a task with a name
 * the partially or fully contains the given keyword.
 */
public class FindFlexCommand extends Command {
    private String keyword;

    /**
     * Instantiates FlexFindCommand.
     *
     * @param keyword The keyword to search for in the list of tasks.
     */
    public FindFlexCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds (case-insensitive) the tasks with names that matches the given keyword exactly.
     *
     * @param tasks The ArrayList of tasks.
     * @param storage The class that reads and write program data to hard drive.
     * @param ui The class that handles interaction with the users.
     * @return A string message to signify the success or failure of task executed.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        return tasks.findFlexibly(this.keyword);
    }
}
