package main.java.duke.task;

import java.util.List;

public class TaskList {
    private List<Task> taskList;

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public void add(Task task) {
        this.taskList.add(task);
    }

    public int size() {
        return this.taskList.size();
    }

    public Task get(int index) {
        return this.taskList.get(index);
    }

    public void remove(int index) {
        this.taskList.remove(index);
    }
    
}
