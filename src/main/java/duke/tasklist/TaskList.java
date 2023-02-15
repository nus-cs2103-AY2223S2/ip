package duke.tasklist;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void save() throws DukeException {
        Storage.save(tasks);
    }

    public void load() throws Exception {
        tasks = Storage.load();
    }

    public boolean taskExists(int index) {
        return index >= 0 && index < tasks.size();
    }

    public Task get(int index) throws DukeException {
        if (!taskExists(index)) {
            throw new DukeException("The index given is out of range");
        }
        return tasks.get(index);
    }

    /**
     * Adds {@code task} to the TaskList instance.
     *
     * @param task Task to be added to the instance.
     * @return The task that was added.
     * @throws DukeException
     */
    public Task add(Task task) throws DukeException {
        tasks.add(task);
        save();
        return task;
    }

    /**
     * Deletes the task at {@code index}.
     *
     * @param index
     * @return The task that was deleted.
     * @throws DukeException
     */
    public Task delete(int index) throws DukeException {
        Task t = get(index);
        tasks.remove(index);
        save();
        return t;
    }

    public int length() {
        return tasks.size();
    }

    /**
     * Marks the task at {@code index} as completed.
     *
     * @param index
     * @return The task that was marked completed.
     * @throws DukeException
     */
    public Task mark(int index) throws DukeException {
        Task t = get(index);
        t.mark();
        save();
        return t;
    }

    /**
     * Marks the task at {@code index} as uncompleted.
     *
     * @param index
     * @return The task that was marked uncompleted.
     * @throws DukeException
     */
    public Task unmark(int index) throws DukeException {
        Task t = get(index);
        t.unmark();
        save();
        return t;
    }

    /**
     * Filters and returns a new instance of TaskList
     * containing only task whose description matches {@code pattern}
     *
     * @param pattern
     * @return A new instance of TaskList with tasks only task whose description matches {@code pattern}
     * @throws DukeException
     */
    public TaskList matches(String pattern) throws DukeException {
        TaskList filtered = new TaskList();
        filtered.tasks = tasks.stream().filter((Task task) -> {
            return task.matches(pattern);
        }).collect(Collectors.toList());
        return filtered;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(String.format("%d. %s", i + 1, tasks.get(i)));
            if (i < tasks.size() - 1) {
                sb.append('\n');
            }
        }
        return sb.toString();
    }
}
