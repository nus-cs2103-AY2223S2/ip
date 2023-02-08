package duke.task;

import duke.exception.DukeException;
import duke.exception.ERROR;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Representation of an ArrayList of Tasks.
 */
public class TaskList extends ArrayList<Task> {

    /**
     * Creates an empty ArrayList of Tasks.
     */
    public TaskList() {
        super();
    }

    /**
     * Creates a TaskList populated with Tasks from the parameter.
     *
     * @param tasks Collection of initial Tasks to add to the TaskList.
     */
    public TaskList(Collection<? extends Task> tasks) {
        super(tasks);
    }

    /**
     * Adds a Task to the list.
     *
     * @param task Task to add.
     */
    public void addTask(Task task) {
        super.add(task);
    }

    /**
     * Deletes a Task from the list based on a given index.
     *
     * @param index 1-based index to delete.
     * @return The Task which has been deleted.
     * @throws DukeException
     */
    public Task deleteTask(int index) throws DukeException {
        checkValidIndex(index);
        assert index >= 1 && index <= super.size();
        Task task = super.get(index - 1);
        super.remove(index - 1);
        return task;
    }

    /**
     * Marks a Task as complete based on a given index.
     *
     * @param index 1-based index to mark.
     * @return The Task which has been marked.
     * @throws DukeException
     */
    public Task markTask(int index) throws DukeException {
        checkValidIndex(index);
        assert index >= 1 && index <= super.size();
        Task task = super.get(index - 1);
        assert task != null;
        task.markAsDone();
        return task;
    }

    /**
     * Unmarks a Task as incomplete based on a given index.
     *
     * @param index 1-based index to unmark.
     * @return The Task which has been unmarked.
     * @throws DukeException
     */
    public Task unmarkTask(int index) throws DukeException {
        checkValidIndex(index);
        assert index >= 1 && index <= super.size();
        Task task = super.get(index - 1);
        assert task != null;
        task.markAsUndone();
        return task;
    }

    /**
     * Checks if the index given is valid based on the list size and returns true if it is valid.
     *
     * @param index 1-based index to check.
     * @return If index is valid, return true, else false.
     * @throws DukeException
     */
    private boolean checkValidIndex(int index) throws DukeException {
        if (index == 0 || index > super.size()) {
            throw new DukeException(String.format(ERROR.INVALID_INDEX.getMessage(), super.size()));
        }
        return true;
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
            assert task != null;
            if (task.toString().substring(7).contains(toFind)) {
                results.addTask(task);
            }
        }

        return results;
    }
}

