package duke;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Handles lists of Task objects using an ArrayList
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> t) {
        this.tasks = t;
    }

    /**
     * Add Task to the TaskList
     */
    public void addTask(Task t) {
        tasks.add(t);
    }

    /**
     * Removes Task from the TaskList
     */
    public void remove(int i) {
        tasks.remove(i);
    }

    /**
     * Retrieves the total number of Tasks in the TaskList
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Retrieves Task from the TaskList based on a given index
     */
    public Task get(int i) {
        return tasks.get(i);
    }

    /**
     * Displays all the tasks in the TaskList as a formatted String
     */
    public String displayTasks() {
        String s = "";
        assert s.length() == 0 : "String s should be empty";
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

    /**
     * Displays all the sorted tasks in the TaskList as a formatted String
     */
    public String displaySorted() {
        // array sorted output sortedStrings,
        // index 0 for to do, index 1 for deadline, index 2 for events

        String[] sortedStrings = {"Todos\n", "Deadlines\n", "Events\n"};
        for (Task t : tasks) {
            int category = 0;
            if (t instanceof Todo) {
                category = 0;
            } else if (t instanceof Deadline) {
                category = 1;
            } else {
                category = 3;
            }
            sortedStrings[category] += "\n" + t;
        }

        String sortedOutput = sortedStrings[0] + "\n" + sortedStrings[1]
                + "\n" + sortedStrings[2];
        return sortedOutput;
    }

    /**
     * Retrieves the arraylist of Tasks in the TaskList
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
}
