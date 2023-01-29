package duke;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> taskArray;

    public TaskList() {
        taskArray = new ArrayList<>();
    }

    public int getNumberOfTasks() {
        return taskArray.size();
    }

    public Task getTask(int index) {
        return taskArray.get(index);
    }

    public void addTask(Task task) {
        taskArray.add(task);
    }

    public void deleteTask(int index) {
        taskArray.remove(index);
    }
}
