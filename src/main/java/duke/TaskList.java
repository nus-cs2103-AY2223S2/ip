package duke;

import java.util.ArrayList;

/** A class TaskList that contains the task list e.g., it has operations to add/delete tasks in the list */
public class TaskList {
    /** The array that stores the list */
    private static ArrayList<Task> tasks;

    /**
     * Initializes an TaskList object with the given array.
     *
     * @param tasks The given array that contains tasks
     */
    public TaskList(ArrayList<Task> tasks){
        this.tasks = tasks;
    }

    /**
     * Return the list of all tasks
     *
     * @return List of all tasks
     */
    public static ArrayList<Task> getList() {
        return tasks;
    }

    /**
     * Mark the task is done with the number of task given
     *
     * @param index The number of task given
     * @return The name of the task marked
     */
    public static String markDone(int index) {
        tasks.get(index).markDone();
        assert tasks.get(index).getStatus().equals("X"): "Task should be marked done";
        return tasks.get(index).getTaskName();
    }

    /**
     * Unmark the task with the number of task given
     *
     * @param index The number of task given
     * @return The name of the task unmarked
     */
    public static String markUndone(int index) {
        tasks.get(index).markUndone();
        assert tasks.get(index).getStatus().equals("O"): "Task should be marked undone";
        return tasks.get(index).getTaskName();
    }

    /**
     * Add the task into the array
     *
     * @param task The task
     */
    public static void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Remove the task with the number of task given
     *
     * @param index The number of task given
     * @return The task removed
     */
    public static Task remove(int index) {
        return tasks.remove(index);
    }

    public static ArrayList<Task> findMatch(String keyword) {
        ArrayList<Task> matchedTasks = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task temp = tasks.get(i);
            if (temp.getTaskName().contains(keyword)) {
                matchedTasks.add(temp);
            }
        }
        return matchedTasks;
    }

    /**
     * Destroy the TaskList
     */
    public static void close() {
        tasks = null;
    }

}
