package duke.task;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javafx.util.Pair;

import duke.DukeException;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private List<Task> taskList;
    private HashMap<Integer, Task> indexToTask;

    /**
     * Constructor for the task list.
     * @param list A list of tasks.
     */
    public TaskList(List<Task> list) {
        taskList = list;
        indexToTask = new HashMap<>();
    }

    /**
     * Returns the arraylist of tasks.
     * @return Arraylist of tasks.
     */
    public List<Task> getList() {
        return this.taskList;
    }

    /**
     * Adds a given task to the list.
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Returns the size of the list of task.
     *
     * @return Integer representing the size of the list.
     */
    public int getSize() {
        return this.taskList.size();
    }

    /**
     * Lists all the tasks in the list in chronological order.
     * Also maps the task index to each task.
     *
     * @return The string representation of the list.
     * @throws DukeException If there is no task available.
     */
    public String listTasks() throws DukeException {
        if (taskList.size() == 0) {
            throw new DukeException("No tasks available.");
        }

        /* Updates indexToTask hashmap and creates a string representing the task list */
        String stringList = convertListToString(taskList);

        return stringList;
    }

    private String convertListToString(List<Task> list) {
        indexToTask.clear(); // Reset hashmap

        IntStream indices = IntStream.range(0, list.size());

        String stringList = indices.peek(index -> indexToTask.put(index, list.get(index)))
                .mapToObj(index -> (index + 1) + ". " + list.get(index))
                .collect(Collectors.joining(System.lineSeparator()));

        String stringListWithoutLastSeparator = stringList.substring(0, stringList.length());
        return stringListWithoutLastSeparator;
    }

    /**
     * Finds all the tasks matching the keywords and updates hashmap.
     *
     * @param keywords Keywords to find the tasks.
     * @return String representation of all the matching tasks.
     */
    public String findTasks(String... keywords) throws DukeException {
        List<Task> filteredTaskList = taskList.stream()
                .filter(task -> task.isMatched(keywords))
                .collect(Collectors.toList());

        if (filteredTaskList.size() == 0) {
            throw new DukeException("No matching task found.");
        }

        return convertListToString(filteredTaskList);
    }

    private void checkIndexOutOfBounds(int idx) throws DukeException {
        boolean isOutOfBound = !indexToTask.containsKey(idx);
        if (isOutOfBound) {
            throw new DukeException("Task index out of bounds.");
        }
    }

    /**
     * Marks the task with the given index as either done or undone, then return that task.
     * Task are 0-indexed.
     *
     * @param idx Index of the task to be marked.
     * @param isDone Status of the task to be marked.
     * @return The task to be marked/unmarked.
     * @throws DukeException If index is out of bounds.
     */
    public Task markTask(int idx, boolean isDone) throws DukeException {
        checkIndexOutOfBounds(idx); // Does this violate SLAP?
        Task taskToMark = indexToTask.get(idx);
        taskToMark.markStatus(isDone);
        return taskToMark;
    }

    /**
     * Deletes the task with the same index.
     *
     * @param idx Index of the task in the list.
     * @return Task deleted.
     * @throws DukeException If index is out of bounds.
     */
    public Task deleteTask(int idx) throws DukeException {
        checkIndexOutOfBounds(idx); // Does this violate SLAP?
        Task t = indexToTask.get(idx);
        taskList.remove(t);
        indexToTask.remove(idx);
        return t;
    }

    /**
     * Returns the task at the given index in the actual ArrayList task list.
     *
     * @param index Index of the task.
     * @return The task at that index.
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Compares an object to this task list.
     *
     * @param o Object to be compared with.
     * @return True if the given object is equal to this task list in value.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof TaskList)) {
            return false;
        }

        // Typecast o to TaskList since we know it is of type TaskList
        TaskList task = (TaskList) o;

        return task.taskList.equals(this.taskList);
    }

    /**
     * Updates the component of the given task and returns the updated task.
     *
     * @param index Index of the task.
     * @param updateComponents List of pairs of components and the detail to update.
     * @return The task updated.
     */
    public Task update(int index, List<Pair<TaskComponent, ?>> updateComponents) throws DukeException {
        checkIndexOutOfBounds(index);

        Task task = indexToTask.get(index);

        for (Pair<TaskComponent, ?> componentPair : updateComponents) {
            TaskComponent component = componentPair.getKey();
            Object detail = componentPair.getValue();
            task.setComponent(component, detail);
        }

        return task;
    }
}
