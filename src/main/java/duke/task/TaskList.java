package duke.task;

import java.util.LinkedList;
import java.util.List;

public class TaskList {
    private List<Task> taskList = new LinkedList<>();

    public TaskList() {}

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
