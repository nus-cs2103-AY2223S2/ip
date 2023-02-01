package features;

import java.util.ArrayList;

import tasks.Task;

/**
 * Contains and modifies tasks entered by the user.
 */
public class TaskList {
    protected ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public void add(Task task) {
        this.list.add(task);
    }

    public int size() {
        return this.list.size();
    }

    public Task get(int index) {
        return this.list.get(index);
    }

    public void remove(int index) {
        this.list.remove(index);
    }
}
