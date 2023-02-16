package duke.task;

import java.util.ArrayList;
import java.util.stream.Collectors;

import duke.DukeException;

/**
 * Deals with tasks in list.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Generates a list of task from specified data.
     */
    public TaskList(String... data) {
        try {
            tasks = new ArrayList<>();
            for (String saveData : data) {
                tasks.add(load(saveData));
            }
        } catch (DukeException e) {
            tasks = new ArrayList<>();
        }
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int size() {
        return tasks.size();
    }

    /**
     * Converts list of tasks to save data.
     *
     * @return Array of task in saved data format.
     */
    public String[] toSaveData() {
        String delimiter = " | ";

        return tasks.stream()
                .map(task -> task.toSaveData(delimiter))
                .toArray(String[]::new);
    }

    /**
     * Generates new task from specified save data.
     *
     * @param saveData The task's save data.
     * @return Generated task.
     * @throws DukeException If the specified save data has missing or invalid values
     */
    public Task load(String saveData) throws DukeException {
        String delimiter = " \\| ";
        switch (saveData.charAt(0)) {
        case ' ':
            return Task.load(saveData, delimiter);
        case 'T':
            return Todo.load(saveData, delimiter);
        case 'D':
            return Deadline.load(saveData, delimiter);
        case 'E':
            return Event.load(saveData, delimiter);
        default:
            throw DukeException.getErrorTaskType();
        }
    }

    /**
     * Adds specified task to list of tasks.
     *
     * @param task duke.task.Task to be added.
     */
    public void add(Task task) {
        assert task != null : "Task given is null";
        tasks.add(task);
    }

    /**
     * Removes specified task from list of tasks.
     *
     * @param index Index of task to be removed.
     * @return The removed task.
     * @throws DukeException If given index is not in the list of tasks.
     */
    public Task delete(int index) throws DukeException {
        try {
            assert index > 0 : "Index given is less than 1";
            return tasks.remove(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw DukeException.getErrorTaskIndexOutOfBounds("removed");
        }
    }

    /**
     * Finds tasks with description containing the specified keyword.
     *
     * @param keyword Index of specified task.
     */
    public ArrayList<Task> find(String keyword) {
        return tasks.stream()
                .filter(task -> task.getDescription()
                        .toUpperCase()
                        .contains(keyword.toUpperCase()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Marks specified task from list of tasks as done.
     *
     * @param index Index of specified task.
     * @return The mark task.
     * @throws DukeException If given index is not in the list of tasks.
     */
    public Task mark(int index) throws DukeException {
        try {
            assert index > 0 : "Index given is less than 1";
            Task task = tasks.get(index - 1);
            task.mark();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw DukeException.getErrorTaskIndexOutOfBounds("marked");
        }
    }

    /**
     * Changes the status of specified task from list of tasks back to not done.
     *
     * @param index Index of specified task.
     * @return The unmarked task.
     * @throws DukeException If given index is not in the list of tasks.
     */
    public Task unmark(int index) throws DukeException {
        try {
            assert index > 0 : "Index given is less than 1";
            Task task = tasks.get(index - 1);
            task.unmark();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw DukeException.getErrorTaskIndexOutOfBounds("unmarked");

        }
    }
}
