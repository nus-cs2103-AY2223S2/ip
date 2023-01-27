package storage;

import java.util.List;

public class TaskList {

    private final List<Task> taskList;

    public TaskList(List<Task> tasks) {
        this.taskList = tasks;
    }

    public int size() {
        return taskList.size();
    }

    public void addTask(Task t) {
        taskList.add(t);
    }

    public void removeTask(int num) {
        taskList.remove(num);
    }

    public Task getTask(int num) {
        return taskList.get(num);
    }

}
