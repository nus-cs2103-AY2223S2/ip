package duke.command;

import duke.exception.DukeException;
import duke.storage.StorageList;
import duke.task.TaskList;


/**
 * Unmark command to indicate task as incomplete.
 */
public class UnmarkCommand extends Command {
    private int taskNum;

    /**
     * Constructor for the unmark command.
     *
     * @param input Input for which task to be unmarked.
     */
    public UnmarkCommand(String input) {
        int taskNumber = Integer.valueOf(input) - 1;
        this.taskNum = taskNumber;

    }

    /**
     * Method to execute the task of marking task as undone.
     *
     * @param tasks   - task list of the current tasks.
     * @param storage - database of the history of commands.
     * @return String Output when the command is unmarked.
     */
    public String execute(TaskList tasks, StorageList storage) throws DukeException {
        tasks.unmarkTask(taskNum);
        return "Task " + (taskNum + 1) + " has been marked as not done.\n" + tasks.getLengthMessage();
    }

}
