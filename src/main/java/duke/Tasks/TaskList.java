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
}
