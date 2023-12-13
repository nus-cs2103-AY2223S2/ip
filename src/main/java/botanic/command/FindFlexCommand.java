package botanic.command;

import botanic.gui.Gui;
import botanic.storage.Storage;
import botanic.task.TaskList;

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
     * Finds (case-insensitive) the tasks with names
     * that matches partially or exactly with the given keyword.
     *
     * @param tasks The TaskList of tasks.
     * @param storage The class that reads and write program data to hard drive.
     * @param gui The class that handles interaction with the users.
     * @return The list of matching tasks found.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Gui gui) {
        return tasks.findFlexibly(keyword);
    }
}
