package peppa;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents a list of tasks and handles all task-related logic.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructs a list of tasks stored in an ArrayList.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Adds task to the end of the current list.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Removes task from the current list.
     *
     * @param task Task to be removed.
     */
    public void deleteTask(Task task) {
        taskList.remove(task);
    }

    /**
     * Searches for tasks in the list which contain the specified keyword.
     *
     * @param keywords Keywords that the user is searching for.
     * @return Subset of tasks in the master list that match the given keyword(s).
     */
    public HashMap<String, ArrayList<Task>> findTasks(String[] keywords) {
        HashMap<String, ArrayList<Task>> map = new HashMap<>();
        for (String keyword : keywords) {
            ArrayList<Task> matchedTasks = new ArrayList<>();
            for (Task task : taskList) {
                if (task.name.contains(keyword)) {
                    matchedTasks.add(task);
                }
            }
            map.put(keyword, matchedTasks);
        }
        return map;
    }

    /**
     * Returns the number of tasks in the list currently.
     *
     * @return Length of tasklist.
     */
    public int getLength() {
        return taskList.size();
    }

    /**
     * Returns the task at the specified position in the list.
     *
     * @param i Index of the task (zero-based).
     * @return Task at position i.
     */
    public Task retrieveTask(int i) {
        return taskList.get(i);
    }
}
