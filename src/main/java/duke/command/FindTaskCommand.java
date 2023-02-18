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

    /**
     * Find all matching tasks and return the output string of all the task combined
     *
     * @param tl TasList to be used to get Task
     * @param ui Ui to output result
     * @param storage Storage to modify if necessary
     * @return output string
     */
    @Override
    public String execute(TaskList tl, Ui ui, Storage storage) {
        ArrayList<Task> taskArr = new ArrayList<Task>();
        for (int i = 0; i < tl.getSize(); i++) {
            Task task = tl.getTask(i);
            String taskname = task.getTaskName();
            if (taskname.contains(keyword)) {
                taskArr.add(task);
            }
        }
        return ui.showFindTaskResult(taskArr);
    }
}
