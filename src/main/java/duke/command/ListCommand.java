package duke.command;

import duke.storage.StorageList;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

<<<<<<< .merge_file_dfM7Lq
/**
 * List command to show the current task list.
 */
public class ListCommand extends Command {
    private ArrayList<Task> list;
=======
public class ListCommand extends Command {
>>>>>>> .merge_file_1LJqwO

    public boolean execute(TaskList tasks, Ui ui, StorageList storage) {
        tasks.printList();
        return true;
    }
}
