package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public void add(Task t) {
        this.taskList.add(t);
    }

    public Task get(int i) {
        return this.taskList.get(i);
    }

    public Task remove(int i) {
        return this.taskList.remove(i);
    }

    public int size() {
        return this.taskList.size();
    }
}
