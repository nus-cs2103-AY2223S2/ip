package duke.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Represents a list of tasks.
 *
 * @author Samarth Verma
 */
public class TaskList implements Iterable<Task> {

    /** The list of tasks. */
    private ArrayList<Task> tasks;
    /** The next id to be assigned to a task. */
    private int nextId;

    /** Creates a new TaskList. */
    public TaskList() {
        tasks = new ArrayList<>();
        nextId = 1;
    }

    /**
     * Creates a new TaskList.
     * @param tasks The list of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
        nextId = tasks.size() + 1;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return the number of tasks in the list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Adds a task to the list.
     *
     * @param t The task to be added.
     */
    public void add(Task t) {
        tasks.add(t);
    }

    /**
     * Removes a task from the list.
     *
     * @param t The task to be removed.
     */
    public void remove(Task t) {
        tasks.remove(t);
    }

    /**
     * Returns the task at the specified index.
     *
     * @param i The index of the task to be returned.
     * @return The task at the specified index.
     */
    public Task get(int i) {
        return tasks.get(i);
    }

    /**
     * Returns the next id to be assigned to a task.
     *
     * @return the next id to be assigned to a task.
     */
    public int nextId() {
        return nextId++;
    }

    /**
     * Returns a sequential Stream with this collection as its source.
     *
     * @return a sequential Stream with this collection as its source.
     */
    public Stream<Task> stream() {
        return tasks.stream();
    }

    /**
     * Returns an iterator over elements of type {@code Task}.
     *
     * @return an Iterator.
     */
    @Override
    public java.util.Iterator<Task> iterator() {
        return tasks.iterator();
    }


}
