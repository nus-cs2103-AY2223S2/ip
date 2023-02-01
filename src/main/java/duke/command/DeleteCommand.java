package duke.command;

import duke.Duke;
import duke.exception.DukeException;
import duke.storage.StorageList;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Delete Command to remove the tasks users do not want, according to its number on the list.
 */
public class DeleteCommand extends Command {
    private int listNum;

    public DeleteCommand(String input) {
        int taskNumberMark = Integer.valueOf(input) - 1;
        this.listNum = taskNumberMark;
    }

    public String execute(TaskList tasks, Ui ui, StorageList storage) throws DukeException {
        tasks.deleteTask(listNum);
        return "Task " + (listNum+1) + " has been deleted from the list\n" + tasks.statement();
    }
}