package features;

import java.util.ArrayList;

import tasks.Task;

/**
 * Contains and modifies tasks entered by the user.
 */
public class TaskList {
    protected ArrayList<Task> values;

    public TaskList() {
        this.values = new ArrayList<>();
    }

    public void add(Task task) {
        this.values.add(task);
    }

    public int size() {
        return this.values.size();
    }

    public Task get(int index) {
        return this.values.get(index);
    }

    public void remove(int index) {
        this.values.remove(index);
    }
}
