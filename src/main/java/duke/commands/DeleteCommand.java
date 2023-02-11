package duke.commands;

import duke.tasks.TaskList;
import duke.utils.DukeIo;

/**
 * DeleteCommand class that allows user to delete tasks.
 */
public class DeleteCommand extends Command {
    private final int INDEX;

    /**
     * Public constructor for delete command.
     * @param index Index (0-based) of task to be deleted from the TaskList.
     */
    public DeleteCommand(int index) {
        this.INDEX = index;
    }

    /**
     * Method to delete task from TaskList
     *
     * @param dukeIo UI class used to return results.
     * @param tasks TaskList containing all tasks to be shown
     * @return Success toast upon deleing task and number of remaining tasks.
     */
    public String exec(DukeIo dukeIo, TaskList tasks) {
        tasks.deleteTask(INDEX);
        return dukeIo.showDeleted(INDEX) + dukeIo.showCount();
    }
}
