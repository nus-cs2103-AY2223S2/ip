package kude.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Representation of a list of tasks
 */
public class TaskList implements Serializable {
    private final List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Add a task to this list
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Returns a readonly {@link Stream} of tasks from this task list
     */
    public Stream<Task> list() {
        return tasks.stream();
    }

    /**
     * Deletes a task from this task list
     * @return Returns true when the task exists and was actually removed from the list
     */
    public boolean delete(Task task) {
        return tasks.remove(task);
    }

    /**
     * Gets the task at the specified index
     */
    public Optional<Task> get(int index) {
        if (index < 0 || index >= tasks.size()) {
            return Optional.empty();
        } else {
            return Optional.of(tasks.get(index));
        }
    }
}
