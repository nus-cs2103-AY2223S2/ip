package duke.command;

import duke.storage.StorageList;
import duke.task.TaskList;
import duke.ui.Ui;

<<<<<<< .merge_file_AShHMN
/**
 * Mark Command to make any task on the list to be done.
 */
=======
>>>>>>> .merge_file_MsFVLV
public class MarkCommand extends Command {
    private int listNum;

    public MarkCommand(String input) {
<<<<<<< .merge_file_AShHMN
        int tasknumbermark = Integer.valueOf(input) - 1;
        this.listNum = tasknumbermark;
=======
        int taskNumberMark = Integer.valueOf(input) - 1;
        this.listNum = taskNumberMark;
>>>>>>> .merge_file_MsFVLV
    }

    public boolean execute(TaskList tasks, Ui ui, StorageList storage) {
        tasks.markTask(listNum);
        return true;
    }
}