package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

/**
 * Find command used for duke.
 *
 * @author Gao Mengqi
 * @version CS2103T AY22/23 Semester 2
 */
public class FindCommand extends Command {
    private String keyword;
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Store all the tasks containing the keyword in an arraylist and print it out.
     * @param tasks
     * @param ui
     * @param storage
     * @return
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        int lenOfTaskList = tasks.getLength();
        ArrayList<Task> relevantTasks = new ArrayList<>();
        for (int i = 0; i < lenOfTaskList; i++) {
            Task currTask = tasks.getTask(i);
            if (currTask.getDescription().contains(keyword)) {
                relevantTasks.add(currTask);
            }
        }

        return ui.showRelevantTasks(relevantTasks);
    }

    /**
     * Check if this command will result in termination of duke.
     *
     * @return whether the program is exited.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
