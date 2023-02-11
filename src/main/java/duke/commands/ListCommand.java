package duke.commands;

import duke.tasks.TaskList;
import duke.utils.DukeIo;

/**
 * List Command lists all the tasks in the given task list.
 */
public class ListCommand extends Command {
    /**
     * Method to return all tasks in the task List
     *
     * @param dukeIo UI class used to return results.
     * @param tasks TaskList containing all tasks to be shown.
     */
    @Override
    public String exec(DukeIo dukeIo, TaskList tasks) {
        return dukeIo.showAll();
    }
}
