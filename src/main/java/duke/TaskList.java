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

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
}
