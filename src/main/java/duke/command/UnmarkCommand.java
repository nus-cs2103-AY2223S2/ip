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

    public UnmarkCommand(String input) {
        int taskNumberMark = Integer.valueOf(input) - 1;
        this.listNum = taskNumberMark;

    }

    public String execute(TaskList tasks, Ui ui, StorageList storage) throws DukeException {
        tasks.unmarkTask(listNum);
        return "Task " + (listNum+1) + " has been marked as not done.\n" + tasks.statement();
    }

}
