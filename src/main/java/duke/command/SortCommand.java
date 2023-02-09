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
     * Sorts deadlines or events, depending on the user choice.
     *
     * @param tasks List of tasks.
     * @param storage Storage object that handles all Storage actions.
     * @throws DukeException Throws exception from the methods called in this method.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        ArrayList<Task> filteredTasks = getFilteredTasks(tasks);
        filteredTasks.sort(Comparator.comparing(Task::getDate));
        return Ui.getSortOutput(filteredTasks);
    }

    /**
     * Gets an array of tasks (either deadline or event).
     *
     * @param tasks List of all tasks.
     * @return String of tasks that have been filtered out.
     * @throws DukeException Thrown if there is a problem getting any tasks.
     */
    private ArrayList<Task> getFilteredTasks(TaskList tasks) throws DukeException {
        if (taskType.equals("d")) {
            return tasks.getDeadlines();
        } else {
            return tasks.getEvents();
        }
    }
}
