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

    public void displayTasks() {
        if (tasks.isEmpty()) {
            System.out.println("    No tasks");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                System.out.println("    " + (i + 1) + ". " + task);
            }
        }
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
}
