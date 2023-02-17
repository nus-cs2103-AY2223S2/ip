package duke.command;

import java.util.ArrayList;
import java.util.Comparator;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Sorts deadlines or events and outputs to the user the sorted list.
 */
public class SortCommand extends Command {
    private final String taskType;

    public SortCommand(String taskType) {
        this.taskType = taskType;
    }

    /**
     * Sorts deadlines or events, depending on the user's choice.
     *
     * @param tasks that will be iterated through to filter out irrelevant tasks.
     * @param storage object that handles all Storage actions.
     * @throws DukeException from the methods called in this method.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        ArrayList<Task> filteredTasks = getFilteredTasks(tasks);
        filteredTasks.sort(Comparator.comparing(Task::getDate));
        return Ui.getFindOrSortOutput(filteredTasks);
    }

    /**
     * Gets an array of tasks (either deadline or event).
     *
     * @param tasks that will be iterated through to filter out irrelevant tasks.
     * @return String of tasks that have been filtered out.
     * @throws DukeException if there is a problem getting any tasks.
     */
    private ArrayList<Task> getFilteredTasks(TaskList tasks) throws DukeException {
        if (taskType.equals("d")) {
            return tasks.getDeadlines();
        } else {
            return tasks.getEvents();
        }
    }

    @Override
    public String toString() {
        return String.format("Sort %s task(s)", taskType);
    }
}
