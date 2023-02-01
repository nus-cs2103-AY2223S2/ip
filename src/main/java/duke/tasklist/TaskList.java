package duke.tasklist;

import duke.exception.DukeException;
import duke.task.Task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> list;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public int getSize() {
        return list.size();
    }


    /**
     * Returns the task at the specified position.
     *
     * @param i Index of the task to return.
     * @return The task at the specified position in the list.
     * @throws DukeException
     */
    public Task getTask(int i) throws DukeException {
        try {
            return list.get(i);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Unable to get duke.task.");
        }
    }

    /**
     * Appends the specified task to the end of the task list.
     *
     * @param t Task to be appended to the list.
     */
    public void addTask(Task t) {
        list.add(t);
    }

    /**
     * Removes the task at the specified position in this list.
     * Shifts any subsequent tasks in the list to the left (subtracts one from their indices).
     *
     * @param i The index of the task to be removed.
     * @return The task that was removed from the task list.
     * @throws DukeException
     */
    public Task removeTask(int i) throws DukeException {
        try {
            Task t = list.remove(i);
            return t;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Unable to remove duke.task.");
        }
    }
}
