package peppa;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;
    public TaskList() {
        taskList = new ArrayList<>();
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void deleteTask(Task task) {
        taskList.remove(task);
    }

    public int getLength() {
        return taskList.size();
    }

    public Task retrieveTask(int i)  {
        return taskList.get(i);
    }
}
