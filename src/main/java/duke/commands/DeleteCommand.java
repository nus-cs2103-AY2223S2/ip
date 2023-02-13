package duke.commands;

import duke.tasks.TaskList;
import duke.utils.DukeIo;

/**
 * DeleteCommand class that allows user to delete tasks.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Public constructor for delete command.
     * @param index Index (0-based) of task to be deleted from the TaskList.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Method to delete task from TaskList
     *
     * @param dukeIo UI class used to return results.
     * @param tasks TaskList containing all tasks to be shown
     * @return Success toast upon deleing task and number of remaining tasks.
     */
    public String exec(DukeIo dukeIo, TaskList tasks) {
        String showDeleted = dukeIo.showDeleted(index);
        tasks.deleteTask(index);
        return showDeleted + dukeIo.showCount();
    }
}
