package task;

import java.util.ArrayList;

/**
 * Manages all Task related operations such as updating
 * the completion status of a task, inserting and deleting
 * the task from the list, and displaying the list.
 */
public class TaskManager {
    private final ArrayList<Task> taskArr;

    /**
     * Initialises the ArrayList.
     */
    public TaskManager() {
        this.taskArr = new ArrayList<>();
    }

    /**
     * Marks the task as completed.
     * @param index
     */
    public void markTask(int index) {
        Task task = taskArr.get(index);
        task.markAsDone();
    }

    /**
     * Marks the task as not completed.
     * @param index
     */
    public void unmarkTask(int index) {
        Task task = taskArr.get(index);
        task.markAsUndone();
    }

    /**
     * Removes the task from the list.
     * @param index
     */
    public void deleteTask(int index) {
        taskArr.remove(index);
    }

    /**
     * Adds a task to the list.
     * @param task
     */
    public void addTaskToList(Task task) {
        taskArr.add(task);
    }

    /**
     * Prints task from list at a given index.
     * @param index
     * @return String representation of a task at a given index
     */
    public String printTask(int index) {
        String str = taskArr.get(index).toString();
        return str;
    }

    /**
     * Iterates through task list to display its elements.
     * @return String of tasks in the list
     */
    public String displayList() {
        if (taskArr.isEmpty()) {
            return "Your list is empty, please add a task!";
        }
        for (int i = 0; i < taskArr.size(); i++) {
            Task tsk = taskArr.get(i);
            return String.format("%d. %s", i + 1, tsk);
        }
        return "";
    }

    public int getTaskArraySize() {
        return taskArr.size();
    }

    public ArrayList<Task> getTaskArr() {
        return taskArr;
    }

}
