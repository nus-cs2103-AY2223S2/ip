package tasklist;

import tasks.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public boolean add(Task task) {
        return taskList.add(task);
    }

    public Task remove(int index) {
        return taskList.remove(index);
    }

    public int size() {
        return taskList.size();
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public Task get(int i) {
        return taskList.get(i);
    }
}
