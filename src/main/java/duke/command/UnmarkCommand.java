package duke.command;

import duke.storage.StorageList;
import duke.task.TaskList;
import duke.ui.Ui;

<<<<<<< .merge_file_zdC212
/**
 * Unmark command to indicate task as incomplete.
 */
=======
>>>>>>> .merge_file_X9WZny
public class UnmarkCommand extends Command {
    private int listNum;

    public UnmarkCommand(String input) {
<<<<<<< .merge_file_zdC212
        int tasknumbermark = Integer.valueOf(input) - 1;
        this.listNum = tasknumbermark;
=======
        int taskNumberMark = Integer.valueOf(input) - 1;
        this.listNum = taskNumberMark;
>>>>>>> .merge_file_X9WZny
    }

    public boolean execute(TaskList tasks, Ui ui, StorageList storage) {
        tasks.unmarkTask(listNum);
        return true;
    }
}