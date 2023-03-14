package duke.command;

import duke.exception.DukeException;
import duke.storage.StorageList;
import duke.task.TaskList;

/**
 * Mark Command to make any task on the list to be done.
 */
public class MarkCommand extends Command {
    private int taskNum;

    /**
     * Constructor for mark command.
     *
     * @param input Task to be marked as completed.
     */
    public MarkCommand(String input) {
        int taskNumber = Integer.valueOf(input) - 1;
        this.taskNum = taskNumber;
    }

    /**
     * Method to execute the marking the task as completed.
     *
     * @param tasks   - task list of the current tasks.
     * @param storage - database of the history of commands.
     * @return String Output when the task is marked complete.
     */
    public String execute(TaskList tasks, StorageList storage) throws DukeException {
        tasks.markTask(taskNum);
        return "Task " + (taskNum + 1) + " has been marked as done.\n" + tasks.getLengthMessage();
    }
}
