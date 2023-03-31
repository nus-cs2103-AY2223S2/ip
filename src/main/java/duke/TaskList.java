package duke;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {
    private final ArrayList<Task> taskList;

    /**
     * Constructor for a TaskList object with an existing ArrayList.
     *
     * @param existing Existing list of Tasks.
     */
    public TaskList(ArrayList<Task> existing) {
        this.taskList = existing;
    }

    /**
     * Constructor for a new TaskList object.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Gets the i-th Task in the TaskList.
     *
     * @param idx Index of the Task to retrieve
     * @return Requested task
     * @throws IndexOutOfBoundsException If supplied index is invalid
     */
    public Task get(int idx) throws IndexOutOfBoundsException {
        return this.taskList.get(idx);
    }

    /**
     * Adds a Task to the TaskList.
     *
     * @param task Task to add
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Deletes a Task from the TaskList.
     *
     * @param idx Index of the task to delete
     * @return The deleted Task object.
     */
    public Task deleteTask(int idx) throws IndexOutOfBoundsException {
        if (idx < 0 || idx >= this.taskList.size()) {
            throw new IndexOutOfBoundsException();
        }

        return this.taskList.remove(idx);
    }

    /**
     * Gets the size of the TaskList.
     *
     * @return An int representing the size of the TaskList.
     */
    public int getSize() {
        return this.taskList.size();
    }

    public List<Task> findAllTasksWithKeyword(String keyword) {
        return this.taskList.stream()
                            .filter(task -> task.doesDescriptionContain(keyword))
                            .collect(Collectors.toList());
    }

    /**
     * Returns true if the tasklist is empty.
     *
     * @return true if the tasklist is empty and false otherwise.
     */
    public boolean isEmpty() {
        return this.taskList.size() == 0;
    }

    /**
     * Gets the underlying ArrayList for the TaskList.
     *
     * @return The ArrayList of Tasks.
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Sorts the ArrayList in place according to task priorities.
     */
    public void sort() {
        Collections.sort(this.taskList);
    }
}
