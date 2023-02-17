package duke.commands;

import duke.tasks.TaskList;
import duke.utils.DukeIo;

/**
 * Find Command that lets user filter for tasks based on key.
 */
public class FindCommand extends Command {
    private final String searchKey;

    /**
     * Public constructor for FindCommand
     * @param searchKey User-input search term
     */
    public FindCommand(String searchKey) {
        this.searchKey = searchKey;
    }

    /**
     * Method to filter tasks TaskList
     *
     * @param dukeIo UI class used to return results.
     * @param tasks TaskList containing all tasks to be shown
     * @return Indexed list of filtered tasks.
     */
    @Override
    public String exec(DukeIo dukeIo, TaskList tasks) {
        return tasks.showFilteredTasks(searchKey);
    }
}
