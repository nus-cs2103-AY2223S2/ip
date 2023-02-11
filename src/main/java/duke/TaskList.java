package duke;

import java.util.ArrayList;

/**
 * Represents a list of all tasks.
 */
public class TaskList {
    ArrayList<Task> taskArray;

    public TaskList() {
        taskArray = new ArrayList<>();
    }

    /**
     * Retrieves the total number of tasks in the list.
     *
     * @return Number of tasks.
     */
    public int getNumberOfTasks() {
        return taskArray.size();
    }

    /**
     * Retrieves the task at a specific index of the list.
     *
     * @param index Index of the list at which the task to be retrieved is at.
     * @return Task at the index of the list.
     */
    public Task getTask(int index) {
        return taskArray.get(index);
    }

    /**
     * Adds new task to the list.
     *
     * @param task Task to be added into the list.
     */
    public void addTask(Task task) {
        taskArray.add(task);
    }

    public void deleteTask(Task task) {
        taskArray.remove(task);
    }

    public String getTaskList() {
        String result = "Here are the tasks in your list:\n";
        for (int i = 0; i < taskArray.size(); i++) {
            result += i + 1 + "." + taskArray.get(i) + "\n";
        }
        return result;
    }
}
