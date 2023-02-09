package duke.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import duke.exception.DukeIndexOutOfBoundsException;
import duke.task.Task;

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

    private void raiseIfInvalidIndex(int index) {
        int n = tasks.size();
        if (index >= 1 && index <= n) {
            return;
        }
        String msg = String.format("Invalid index - valid index is from 1 to %d", n);
        throw new DukeIndexOutOfBoundsException(msg);
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
     * Counts the number of stored tasks.
     *
     * @return a string representing the number of stored tasks
     */
    public String countTaskAsString() {
        int n = tasks.size();
        return String.format("%d task%s", n, n < 2 ? "" : "s");
    }

    private <T> String listFromStreamWithIndicies(Stream<T> stream) {
        return stream.map(new Function<T, String>() {
            private int index = 1;

            @Override
            public String apply(T element) {
                String out = String.format("%d.%s", index, element);
                index++;
                return out;
            }
        }).collect(Collectors.joining("\n"));
    }

    /**
     * Lists all stored tasks, with indicies. Indicies start from {@code 1}.
     *
     * @return a string showing the contents of all tasks, with indicies.
     */
    public String listAllTasks() {
        return listFromStreamWithIndicies(tasks.stream());
    }

    /**
     * Lists all stored tasks that contain the given keyword, with indicies. Indicies start from
     * {@code 1}.
     *
     * @param keyword the given keyword to search for
     * @return a string showing the contents of all tasks that contain the keyword, with indicies
     */
    public String listTasksContainKeyword(String keyword) {
        return listFromStreamWithIndicies(tasks.stream().filter(task -> task.contains(keyword)));
    }

    /**
     * Lists all unique descriptions, with counts and indicies.
     *
     * @return a string showing the descriptions and their counts
     */
    public String listUniqueTaskDescriptionsWithCounts() {
        return listFromStreamWithIndicies(tasks.stream().collect(Collectors
                .groupingBy(Task::getDescription, LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream().map(entry -> {
                    String description = entry.getKey();
                    long count = entry.getValue();
                    return String.format("%s (appeared %d time%s)", description, count,
                            count < 2 ? "" : "s");
                }));
    }

    @Override
    public String toString() {
        return "TaskList:\n" + listAllTasks();
    }
}
