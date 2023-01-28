package duke.task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }
    public int size() {
        return this.taskList.size();
    }
    public Task get(int index) {
        return this.taskList.get(index);
    }
    public void add(Task t) {
        this.taskList.add(t);
    }
    public void remove(int index) {
        this.taskList.remove(index);
    }
}
