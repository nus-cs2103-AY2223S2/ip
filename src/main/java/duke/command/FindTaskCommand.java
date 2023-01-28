package duke.command;

import java.util.ArrayList;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Class in charge of handling the case to display all task matching keyword
 */
public class FindTaskCommand extends Command {
    private String keyword;

    public FindTaskCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        ArrayList<Task> taskArr = new ArrayList<Task>();
        for (int i = 0; i < tl.getSize(); i++) {
            Task task = tl.getTask(i);
            String taskname = task.getTaskName();
            if (taskname.contains(keyword)) {
                taskArr.add(task);
            }
        }
        ui.showFindTaskResult(taskArr);
    }
}
