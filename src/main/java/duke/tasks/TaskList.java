package duke.tasks;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import duke.utils.DukeIo;

/**
 * Iterator class that maintains a List of all tasks
 */
public class TaskList {
    private static List<Task> allTasks;
    private static int taskCount = 0;
    private static DukeIo dukeIo = new DukeIo();

    /**
     * Restricted constructor that initalises the TaskList object.
     * @param tasks List of tasks.
     */
    private TaskList(List<Task> tasks) {
        allTasks = tasks;
        taskCount = tasks.size();
    }

    /**
     * Factory method to create a TaskList object with emty List of tasks.
     * @return An empty TaskList.
     */
    public static TaskList ofNull() {
        return new TaskList(new ArrayList<Task>());
    }

    /**
     * Initialises static variables from pre-existing list of tasks.
     * @param tasks List of tasks to be loaded from.
     */
    public void loadFrom(List<Task> tasks) {
        allTasks = tasks;
        taskCount = tasks.size();
    }

    /**
     * Indicates the number of existing tasks in the TaskList
     * @return taskCount int number of tasks
     */
    public static int getTaskCount() {
        return taskCount;
    }

    /**
     * Retreives the task from the TaskList given the index of the task.
     * @param taskIndex 0-based index of task to retrieve from task list.
     * @return Task object
     */
    public Task getTask(int taskIndex) {
        return allTasks.get(taskIndex);
    }

    /**
     * Retrieves all tasks in the TaskList.
     * @return ArrayList of tasks
     */
    public static ArrayList<Task> getAllTasks() {
        return new ArrayList<>(allTasks);
    }

    /**
     * Adds a task to TaskList.
     * Increments the number of task count by 1.
     * @param task Task object added to the TaskList.
     */
    public void addTask(Task task) {
        allTasks.add(task);
        taskCount++;
    }

    /**
     * Removes a task from the TaskList given the index of the task.
     * Decrements the number of task count by 1.
     * @param taskIndex int index of taks to be deleted.
     */
    public void deleteTask(int taskIndex) {
        allTasks.remove(taskIndex);
        taskCount--;
    }

    /**
     * Filters all tasks to display a list of tasks containing user input keywords.
     * @param searchString Keyword to search
     * @return List of filtered tasks
     */
    public String showFilteredTasks(String searchString) {
        // early return if there are no tasks initially
        if (allTasks.size() == 0) {
            return dukeIo.notifyZeroHits();
        }
        List<Task> searchResults = allTasks.stream()
                                            .filter(t -> t.toString().contains(searchString))
                                            .collect(Collectors.toList());
        if (searchResults.size() == 0) {
            return dukeIo.notifyZeroHits();
        } else {
            return dukeIo.showFiltered(searchResults);
        }
    }

    public String sort(Comparator<Task> c) {
        allTasks = allTasks.stream()
                            .sorted(c)
                            .collect(Collectors.toList());
        return dukeIo.showAll();
    }
}
