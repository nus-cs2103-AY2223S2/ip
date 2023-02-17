package duke;

import duke.task.Task;

import java.util.ArrayList;

// class TaskList - handles lists of Task objects using an ArrayList
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> t) {
        this.tasks = t;
    }

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public int size() {
        return this.tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void remove(int index) {
        tasks.remove(index);
    }

    public void set(int index, Task task) {
        tasks.set(index, task);
    }
}
