package duke.command;

import duke.exception.DukeException;
import duke.storage.StorageList;
import duke.task.TaskList;

/**
 * Mark Command to make any task on the list to be done.
 */
public class MarkCommand extends Command {
    private int listNum;

    /**
     * Constructor for mark command.
     *
     * @param input Task to be marked as completed.
     */
    public MarkCommand(String input) {
        int taskNumberMark = Integer.valueOf(input) - 1;
        this.listNum = taskNumberMark;
    }

    /**
     * Method to execute the marking the task as completed.
     *
     * @param tasks   - task list of the current tasks.
     * @param storage - database of the history of commands.
     * @return String Output when the task is marked complete.
     */
    public String execute(TaskList tasks, StorageList storage) throws DukeException {
        tasks.markTask(listNum);
        return "Task " + (listNum + 1) + " has been marked as done.\n" + tasks.statement();
    }
}
