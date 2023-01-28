package duke.command;

import duke.storage.StorageList;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Delete Command to remove the tasks users do not want, according to its number on the list.
 */
public class DeleteCommand extends Command {
    private int listNum;

    /**
     * @param input
     */
    public DeleteCommand(String input) {
        int taskNumberMark = Integer.valueOf(input) - 1;
        this.listNum = taskNumberMark;
    }

    /**
     * @param tasks   - task list of the current tasks.
     * @param ui      - interface of the command.
     * @param storage - database of the history of commands.
     * @return
     */
    public boolean execute(TaskList tasks, Ui ui, StorageList storage) {
        tasks.deleteTask(listNum);
        return true;
    }

}
