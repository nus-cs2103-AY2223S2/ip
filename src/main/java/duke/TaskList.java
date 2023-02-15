package duke;

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

    public String displayTasks() {
        String s = "";
        if (tasks.isEmpty()) {
            s += "    No tasks";
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                s += "    " + (i + 1) + ". " + task;
                s += i != tasks.size() ? "\n" : "";
            }
        }
        return s;
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
}
