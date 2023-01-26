package duke;

import java.util.ArrayList;

/** A class TaskList that contains the task list e.g., it has operations to add/delete tasks in the list */
public class TaskList {
    /** The array that stores the list */
    private static ArrayList<Task> arr;

    /**
     * Initializes an TaskList object.
     *
     * @return A TaskList instance
     */
    public TaskList(){
        arr = new ArrayList<>();
    }

    /**
     * Initializes an TaskList object with the given array.
     *
     * @param arr The given array that contains tasks
     * @return A TaskList instance
     */
    public TaskList(ArrayList<Task> arr){
        this.arr = arr;
    }

    public static ArrayList<Task> getList() {
        return arr;
    }

    /**
     * Mark the task is done with the number of task given
     *
     * @param index The number of task given
     * @return The name of the task marked
     */
    public static String markDone(int index) {
        arr.get(index).markDone();
        return arr.get(index).getTaskName();
    }

    /**
     * Unmark the task with the number of task given
     *
     * @param index The number of task given
     * @return The name of the task unmarked
     */
    public static String markUndone(int index) {
        arr.get(index).markUndone();
        return arr.get(index).getTaskName();
    }

    /**
     * Add the task into the array
     *
     * @param task The task
     */
    public static void addTask(Task task) {
        arr.add(task);
    }

    /**
     * Remove the task with the number of task given
     *
     * @param index The number of task given
     * @return The task removed
     */
    public static Task remove(int index) {
        return arr.remove(index);
    }

    public static ArrayList<Task> findMatch(String keyword) {
        ArrayList<Task> matchedTasks = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) {
            Task temp = arr.get(i);
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
        arr = null;
    }

}
