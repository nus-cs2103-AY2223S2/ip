package duke.commands;

import duke.utils.DukeIo;
import duke.tasks.TaskList;
import duke.tasks.Task;

/**
 * MarkCommand class which handles both mark and unmark commands 
 */
public class MarkCommand extends Command {
    private final int INDEX;

    /**
     * Public constructor for Mark/unmark command.
     * @param index User-input task index to mark/ unmark.
     */    
    public MarkCommand(int index) {
        this.INDEX = index;
    }

    /**
     * Method to mark/ unmark task in the TaskList.
     * 
     * @param dukeIo UI class used to return results
     * @param tasks TaskList containing all tasks to be marked/ unmarked.
     * @return UI feedback after successful mark and change in task status.
     */
    @Override
    public String exec(DukeIo dukeIo, TaskList tasks) {
        Task task = tasks.getTask(INDEX);
        if (!task.isDone()) {
            task.toggleDoneOrNot();
            return dukeIo.notifySuccessComplete(task);
        } else {
            return dukeIo.notifyMarkFail(task);
        }
    }
}
