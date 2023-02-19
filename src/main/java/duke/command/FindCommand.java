package duke.command;

import java.util.ArrayList;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Find command used for duke.
 *
 * @author Gao Mengqi
 * @version CS2103T AY22/23 Semester 2
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructor for FindCommand object
     * @param keyword
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Stores all the tasks containing the keyword in an arraylist and print it out.
     * @param tasks
     * @param ui
     * @param storage
     * @return
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (keyword.isBlank()) {
            return ui.showEmptyKeywordMsg();
        }

        int totalNumOfTasks = tasks.getLength();
        ArrayList<Task> tasksWithKeyword = new ArrayList<>();

        for (int i = 0; i < totalNumOfTasks; i++) {
            Task currTask = tasks.getTask(i);
            if (currTask.getDescription().contains(keyword)) {
                tasksWithKeyword.add(currTask);
            }
        }

        return tasksWithKeyword.isEmpty() ? ui.showNoRelevantTasksMsg() : ui.showRelevantTasks(tasksWithKeyword);
    }

    /**
     * Checks if this command will result in termination of duke.
     *
     * @return whether the program is exited.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
