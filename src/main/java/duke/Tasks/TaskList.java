package duke.Tasks;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    public static List<Task> allTasks;
    public static int taskCount = 0;

    private TaskList(List<Task> tasks) {
        allTasks = tasks;
        taskCount = tasks.size();
    }

    public static TaskList ofNull() {
        return new TaskList(new ArrayList<Task>());
    }

    public void loadFrom(List<Task> tasks) {
        allTasks = tasks;
        taskCount = tasks.size();
    }

    public int getTaskCount() {
        return taskCount;
    }

    public Task getTask(int taskIndex) {
        return allTasks.get(taskIndex);
    } 

    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(allTasks);
    }

    public void addTask(Task task) {
        allTasks.add(task);
        taskCount++;
    }    

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
