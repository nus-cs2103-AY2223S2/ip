package duke.task;

import java.util.HashMap;
import java.util.List;

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
        indexToTask.clear(); // Reset hashmap

        if (taskList.size() == 0) {
            throw new DukeException("No tasks available.");
        }

        StringBuilder stringList = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            stringList.append((i + 1) + ". " + task);
            indexToTask.put(i, task); // update hashmap
            stringList.append(System.lineSeparator());
        }

        stringList.deleteCharAt(stringList.length() - 1); // Deletes the last lineSeparator.
        return stringList.toString();
    }

    private void checkIndexOutOfBounds(int idx) throws DukeException {
        if (idx < 0 || idx >= indexToTask.size()) {
            throw new DukeException("Task index out of bounds.");
        }
    }

    /**
     * Mark the task with the given index as either done or undone.
     * Task are 0-indexed.
     *
     * @param idx Index of the task to be marked.
     * @param isDone Status of the task to be marked.
     * @throws DukeException If index is out of bounds.
     */
    public void markTask(int idx, boolean isDone) throws DukeException {
        checkIndexOutOfBounds(idx); // Does this violate SLAP?
        Task t = indexToTask.get(idx);
        t.markStatus(isDone);
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
     * Returns the task at the given index in the ArrayList task list.
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
    * Finds all the tasks matching the keywords.
    *
    * @param keywords Keywords to find the tasks.
    * @return String representation of all the matching tasks.
    */
    public String findTasks(String... keywords) throws DukeException {
        indexToTask.clear(); // Reset Hashmap

        /* Forms a string to represent list of matching tasks */
        int idx = 0;
        StringBuilder foundTasks = new StringBuilder();
        for (Task t : this.taskList) {
            String description = t.getDescription();

            for (String keyword : keywords) {
                if (description.contains(keyword)) {
                    foundTasks.append((idx + 1) + ". " + t + System.lineSeparator());
                    indexToTask.put(idx, t);
                    idx++;
                    break; // Break out of inner for loop
                }
            }

        }

        if (foundTasks.length() == 0) {
            throw new DukeException("No matching task found.");
        }

        foundTasks.deleteCharAt(foundTasks.length() - 1); // Deletes the last lineSeparator.
        return foundTasks.toString();
    }
}
