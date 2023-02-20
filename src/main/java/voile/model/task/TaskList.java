package voile.model.task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import voile.common.exception.VoileIndexOutOfBoundsException;

/**
 * A wrapper around {@code List<Task>}.
 * <p>
 * This class contains some basic utilities of a {@code List}, while also provides some special
 * methods to work with {@code Task}.
 */
public class TaskList implements Serializable {

    private static final long serialVersionUID = 1094392670804693665L;

    private List<Task> tasks;

    /**
     * Initializes an empty {@code TaskList}.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Initializes a {@code TaskList} by copying the given list of tasks.
     *
     * @param tasks the list of tasks to populate this {@code TaskList}
     */
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Creates a new {@code TaskList} with the given tasks.
     *
     * @param tasks an array of tasks
     * @return a new {@code TaskList} contains the given tasks
     */
    public static TaskList of(Task... tasks) {
        return new TaskList(Arrays.asList(tasks));
    }

    private void raiseIfInvalidIndex(int index) {
        int n = tasks.size();
        if (index >= 1 && index <= n) {
            return;
        }
        String msg = String.format("Invalid index - valid index is from 1 to %d", n);
        throw new VoileIndexOutOfBoundsException(msg);
    }

    /**
     * Adds a new task into this {@code TaskList}.
     *
     * @param task the task to be added
     * @return {@code true} if the task was added, otherwise {@code false}
     */
    public boolean add(Task task) {
        return tasks.add(task);
    }

    /**
     * Gets the task located at the given index.
     *
     * @param index the index of the task
     * @return the accessed task
     */
    public Task get(int index) {
        raiseIfInvalidIndex(index);
        return tasks.get(index - 1);
    }

    /**
     * Removes the task located at the given index.
     *
     * @param index the index of the task
     * @return the removed task
     */
    public Task remove(int index) {
        raiseIfInvalidIndex(index);
        return tasks.remove(index - 1);
    }

    /**
     * Returns the size of this {@code TaskList}.
     *
     * @return the number of tasks stored in this {@code TaskList}
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Checks whether this {@code TaskList} is empty or not.
     *
     * @return {@code true} if this {@code TaskList} is empty, otherwise {@code false}
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Clears this {@code TaskList}.
     */
    public void clear() {
        tasks.clear();
    }

    /**
     * Returns a {@code Stream} of tasks in this {@code TaskList}.
     *
     * @return a {@code Stream} of tasks in this {@code TaskList}
     */
    public Stream<Task> stream() {
        return tasks.stream();
    }

    @Override
    public String toString() {
        return "TaskList:" + tasks;
    }
}
