package duke.command;

import java.util.ArrayList;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Finds task when user input states find.
 */
public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds task from the list of tasks using the keyword provided.
     *
     * @param tasks that will be searched through to find matching tasks.
     * @param storage object that handles all Storage actions.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        ArrayList<Task> foundTasks = tasks.findTask(keyword);
        return Ui.getFindOrSortOutput(foundTasks);
    }
}
