package duke.command;

import duke.exception.DukeException;
import duke.storage.StorageList;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Unmark command to indicate task as incomplete.
 */
public class UnmarkCommand extends Command {
    private int listNum;

    /**
     * Constructor for the unmark command.
     *
     * @param input Input for which task to be unmarked.
     */
    public UnmarkCommand(String input) {
        int taskNumberMark = Integer.valueOf(input) - 1;
        this.listNum = taskNumberMark;

    }

    /**
     * Method to execute the task of marking task as undone.
     *
     * @param tasks   - task list of the current tasks.
     * @param ui      - interface of the command.
     * @param storage - database of the history of commands.
     * @return String Output when the command is unmarked.
     */
    public String execute(TaskList tasks, Ui ui, StorageList storage) throws DukeException {
        tasks.unmarkTask(listNum);
        return "Task " + (listNum + 1) + " has been marked as not done.\n" + tasks.statement();
    }

}
