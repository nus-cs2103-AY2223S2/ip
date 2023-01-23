package duke;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a List of Tasks. Uses basic methods from ArrayList.
 */
public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the List of Tasks.
     * @return List of Tasks.
     */
    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Adds a Task to the TaskList.
     *
     * @param newTask The new task to be added.
     * @return TaskList with added task.
     */
    public TaskList add(Task newTask) {
        TaskList newList = new TaskList(tasks);
        newList.tasks.add(newTask);
        return newList;
    }

    /**
     * Removes a Task from the TaskList.
     *
     * @param index The index of the task to be removed.
     * @return TaskList with removed task.
     */
    public TaskList remove(int index) {
        TaskList newList = new TaskList(tasks);
        newList.tasks.remove(index);
        return newList;
    }

    /**
     * Replaces a Task in the TaskList with another Task.
     *
     * @param index The index of the task to be replaced.
     * @param newTask The new task that replaces the existing task.
     * @return TaskList with replaced task.
     */
    public TaskList set(int index, Task newTask) {
        TaskList newList = new TaskList(tasks);
        newList.tasks.set(index, newTask);
        return newList;
    }

    /**
     * Returns the Task at the index.
     *
     * @param index The index of the task to be retrieved.
     * @return Task at the index.
     */
    public Task get(int index) {
        return this.tasks.get(index);
    }

    /**
     * Returns size of TaskList.
     * @return Size of TaskList.
     */
    public int size() {
        return this.tasks.size();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        int num = 1;
        for (Task a : tasks) {
            str.append(num).append(". ").append(a);
            if (num != tasks.size()) {
                str.append("\n");
            }
            num++;
        }
        if (num == 1) {
            str.append("there are no items in your task list");
        }
        return str.toString();
    }
}
