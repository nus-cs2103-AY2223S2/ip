package task;

import task.Task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TaskList implements Serializable {
    private final List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public void delete(int i) {
        this.tasks.remove(i);
    }

    public Task get(int i) {
        return this.tasks.get(i);
    }

    public int size() {
        return this.tasks.size();
    }

    public String[] toStringArray() {
        String[] list = new String[this.tasks.size()];
        Task tsk;
        for (int i = 0; i < this.tasks.size(); i++) {
            tsk = this.tasks.get(i);
            list[i] = String.format(" %s", tsk);
        }
        return list;
    }

}
