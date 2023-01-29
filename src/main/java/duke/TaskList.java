package duke;

import java.util.ArrayList;

public class TaskList {
    public static ArrayList<Task> allTasks = new ArrayList<>();
    public static int taskCount = 0;

    private TaskList(ArrayList<Task> tasks) {
        allTasks = tasks;
        taskCount = tasks.size();
    }

    public static TaskList ofNull() {
        return new TaskList(new ArrayList<Task>());
    }

    public void loadFrom(ArrayList<Task> tasks) {
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
        return allTasks;
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
