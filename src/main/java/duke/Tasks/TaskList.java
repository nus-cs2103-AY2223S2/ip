package duke.Tasks;

import java.util.ArrayList;
import java.util.List;

/** Iterator class that maintains a List of all tasks. */
public class TaskList {
    public static List<Task> allTasks;
    public static int taskCount = 0;

    /**
     * Restricted constructor that initialises the TaskList object.
     * @param tasks List of tasks to be stored.
     */
    private TaskList(List<Task> tasks) {
        allTasks = tasks;
        taskCount = tasks.size();
    }

    /**
     * Static factory method to create a TaskList object with empty List of tasks.
     * @return new TaskList object with no tasks.
     */
    public static TaskList ofNull() {
        return new TaskList(new ArrayList<Task>());
    }

    /**
     * Initialises list of tasks and taskCount from pre-existing list of tasks.
     * 
     * @param tasks
     */
    public void loadFrom(List<Task> tasks) {
        allTasks = tasks;
        taskCount = tasks.size();
    }

    /**
     * Indicates the number of existing tasks in the TaskList
     * 
     * @return taskCount number of tasks in the TaskList.
     */
    public int getTaskCount() {
        return taskCount;
    }

    /**
     * Retreives the task from TaskList given the index of the task.
     * 
     * @param taskIndex index of Task in TaskList to be accessed.
     * @return Task Object.
     */
    public Task getTask(int taskIndex) {
        return allTasks.get(taskIndex);
    } 

    /**
     * Retrieves all tasks in the TaskList.
     * 
     * @return ArrayList of tasks
     */
    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(allTasks);
    }

    /**
     * Adds a task to TaskList.
     * Increments the number of task count by 1
     * 
     * @param task Task object to be added to the list.
     */
    public void addTask(Task task) {
        allTasks.add(task);
        taskCount++;
    }    

    /**
     * Removes a task form TaskList with the given index.
     * Decrements the number of task count by 1.
     * 
     * @param taskIndex Index of task to be deleted.
     */
    public void deleteTask(int taskIndex) {
        allTasks.remove(taskIndex);
        taskCount--;
    }

    /**
     * Filters from list of all tasks to return a new list of tasks containing the search term.
     * @param serachString Keyword to search
     * @return void Displays the filtered tasks
     */
    public void showFilteredTasks(String searchString) {
        // early return if there are no tasks initially
        if (allTasks.size() == 0) {
            dukeIo.notifyZeroHits();
            return;
        }

        List<Task> searchResults = allTasks.stream()
                                            .filter(t -> t.toString().contains(searchString))
                                            .collect(Collectors.toList());
        if (searchResults.size() == 0) {
            dukeIo.notifyZeroHits();
            return;
        } else {
            dukeIo.showFiltered(searchResults);
            return;
        }
    }
}
