package duke.util;

import java.util.ArrayList;
import java.util.List;

/**
 * An implementation of the {@code ArrayList} to
 * store the {@code Task} assigned from the user
 * and perform required actions on them.
 */

public class TaskList {
    private List<Task> listTask;

    /**
     * Constructs an empty list of {@code Task}.
     */

    public TaskList() {
        this.listTask = new ArrayList<Task>();
    }

    /**
     * Marks the {@code Task} at the specified position as done.
     * If tasks is already done, nothing happens.
     *
     *
     * @param index index of the specified {@code Task} that user
     *              wants to mark as done
     * @return the list with the specified tasks marked as done
     * @throws duke.io.input.exception.UserInputException if index is out of range
     *              of the list of tasks
     */

    public TaskList markTask(int index) {
        Task currentTask = this.listTask.get(index);
        this.listTask.set(index, currentTask.markDone());
        return this;
    }

    /**
     * Unmarks the {@code Task} at the specified position if it's already done.
     * Tasks will remain the same if they're not marked as done.
     *
     * @param index index of the specified {@code Task} that user
     *              wants to unmark
     * @return return the list with the specified task unmarked
     * @throws duke.io.input.exception.UserInputException if index is out of range
     *              of the list of tasks
     */

    public TaskList unmarkTask(int index) {
        Task currentTask = this.listTask.get(index);
        this.listTask.set(index, currentTask.unMark());
        return this;
    }

    /**
     * Adds {@code Task} to the list of tasks.
     *
     * @param task the {@code Task} that user
     *              wants to add
     * @return the list of task with the new task added
     */

    public TaskList addTask(Task task) {
        this.listTask.add(task);
        return this;
    }

    /**
     * Retrieve {@code Task} to the list of tasks,
     * return type is {@code Task}.
     *
     * @param index in dex of the {@code Task}
     *               to retrieve
     *
     * @return the task at the specified index
     */

    public Task getTask(int index) {
        assert (index >= 0);
        return this.listTask.get(index);
    }

    /**
     * Return that size of the list of tasks,
     * which is also the current number of tasks.
     *
     * @return the number of task
     */

    public int getSize() {
        return this.listTask.size();
    }

    /**
     * Removed a {@code Task} from a specified position from the list of tasks
     *
     * @return the new list of task with the specified task removed
     */

    public TaskList removeTask(int index) {
        assert (index >= 0);
        this.listTask.remove(index);
        return this;
    }

    /**
     * Return the index of a specified {@code Task} in the list of tasks
     *
     * @param task the {@code Task} to search index
     * @return index of the specified {@code Task}
     */

    public int searchIndexOf(Task task) {
        int index = -1;
        for (int i = 0; i < this.getSize(); i++) {
            if (this.getTask(i).toString().equals(task.toString())) {
                index = i;
                return index;
            }
        }
        return index;
    }

    @Override
    public String toString() {
        if (this.listTask.isEmpty()) {
            return "NOTHING ADDED TO LIST";
        } else {
            String toPrintOut = "";
            for (int i = 0; i < this.listTask.size(); i++) {
                toPrintOut += (i + 1) + ". " + this.listTask.get(i).toString() + '\n';
            }
            return toPrintOut;
        }
    }
}
