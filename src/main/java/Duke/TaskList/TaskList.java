package Duke.TaskList;
import Duke.Task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    public TaskList() {}

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public int getSize() {
        return this.tasks.size();
    }

    public Task getTask (int index) {
        return this.tasks.get(index);
    }

    public void remove (int index) {
        this.tasks.remove(index);
    }

    public void mark (int index) {
        this.tasks.get(index).setStatus(true);
    }

    public void unmark (int index) {
        this.tasks.get(index).setStatus(false);
    }
}
