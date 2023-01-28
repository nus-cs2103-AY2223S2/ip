package duke.task;

import java.util.ArrayList;
import java.io.Serializable;

public class TaskList implements Serializable {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }

    public void add(Task t) {
        this.tasks.add(t);
    }

    public void delete(int index) {
        this.tasks.remove(index);
    }

    public int size() {
        return this.tasks.size();
    }

    @Override
    public String toString() {
        return this.tasks.toString();
    }
}
