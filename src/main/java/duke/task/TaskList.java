package duke.task;

import duke.exception.DukeException;
import duke.exception.ERROR;

import java.util.ArrayList;
import java.util.Collection;

public class TaskList extends ArrayList<Task> {

    public TaskList() {
        super();
    }

    public TaskList(Collection<? extends Task> tasks) {
        super(tasks);
    }

    public void addTask(Task task) {
        super.add(task);
    }

    public Task deleteTask(int index) throws DukeException {
        checkSize(index);
        Task task = super.get(index - 1);
        super.remove(index - 1);
        return task;
    }

    public Task markTask(int index) throws DukeException {
        checkSize(index);
        Task task = super.get(index - 1);
        task.markAsDone();
        return task;
    }

    public Task unmarkTask(int index) throws DukeException {
        checkSize(index);
        Task task = super.get(index - 1);
        task.markAsUndone();
        return task;
    }

    /**
     * Returns a new TaskList containing all the Tasks containing a given keyword.
     *
     * @param toFind The keyword to search for.
     * @return New TaskList containing the relevant tasks.
     */
    public TaskList findTasks(String toFind) {
        TaskList results = new TaskList();

        for (int i = 0; i < super.size(); ++i) {
            Task task = super.get(i);
            if (task.toString().substring(7).contains(toFind)) {
                results.addTask(task);
            }
        }

        return results;
    }

    private boolean checkSize(int index) throws DukeException {
        if (index == 0 || index > super.size()) {
            throw new DukeException(String.format(ERROR.INVALID_INDEX.getMessage(), super.size()));
        }
        return true;
    }
}
