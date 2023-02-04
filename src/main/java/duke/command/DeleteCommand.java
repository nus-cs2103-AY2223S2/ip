package duke.command;

import duke.exception.DukeException;
import duke.storage.StorageList;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Delete Command to remove the tasks users do not want, according to its number on the list.
 */
public class DeleteCommand extends Command {
    private int listNum;

    /**
     * Constructor for the delete command.
     *
     * @param input The task that the user wants to delete.
     */
    public DeleteCommand(String input) {
        int taskNumberMark = Integer.valueOf(input) - 1;
        this.listNum = taskNumberMark;
    }

    /**
     * Execute method which displays the message number that has been deleted.
     *
     * @param tasks   - task list of the current tasks.
     * @param ui      - interface of the command.
     * @param storage - database of the history of commands.
     * @return String displays the task number and message which is deleted.
     */
    public String execute(TaskList tasks, Ui ui, StorageList storage) throws DukeException {
        tasks.deleteTask(listNum);
        return "Task " + (listNum + 1) + " has been deleted from the list\n" + tasks.getLengthMessage();
    }

}
