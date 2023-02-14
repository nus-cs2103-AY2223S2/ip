package duke;

import java.util.ArrayList;
import java.util.Iterator;

import duke.task.Task;

/**
 * Represents a class to store a user's tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private int count;

    /**
     * Constructor for a TaskList object.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Prints the user's tasks in list form.
     */
    public String list() {
        int curr = 1;
        String toPrint = "Here are your tasks:\n";
        Iterator<Task> iter = this.tasks.iterator();
        while (iter.hasNext()) {
            Task task = iter.next();
            toPrint += curr + ". " + task + "\n";
            curr++;
        }
        return toPrint;
    }

    /**
     * Prints the user's tasks that matches keyword in list form.
     */
    public String findTask(String keyword) {
        int curr = 1;
        String toPrint = "";
        Iterator<Task> iter = this.tasks.iterator();
        while (iter.hasNext()) {
            Task task = iter.next();
            if (task.getName().contains(keyword)) {
                toPrint += curr + ". " + task + "\n";
                curr++;
            }
        }
        return toPrint;
    }

    /**
     * Adds a task to the user's TaskList.
     * @param t Task to be added.
     */
    public void addTask(Task t) {
        this.tasks.add(t);
        count++;
    }

    /**
     * Removes a task from the user's TaskList.
     * @param i Index of task to be deleted.
     */
    public String deleteTask(int i) {
        Task t = this.tasks.remove(i);
        count--;
        return "Deleted task:\n  " + t + "\nNumber of tasks: " + count;
    }

    /**
     * Marks the given task as completed.
     * @param i Index of task to be marked.
     */
    public String markTask(int i) {
        Task t = this.tasks.get(i);
        t.mark();
        return "Task has been marked as done:\n " + t;
    }

    /**
     * Unmarks the given task as completed.
     * @param i Index of task to be unmarked.
     */
    public String unmarkTask(int i) {
        Task t = this.tasks.get(i);
        t.unmark();
        return "Task has been marked as not done:\n " + t;
    }

    /**
     * Returns the number of tasks in the user's TaskList.
     * @return int Number of tasks
     */
    public int size() {
        return count;
    }

    /**
     * Returns the user's tasks in an ArrayList.
     * @return ArrayList User's task
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public Task get(int i) {
        return this.tasks.get(i);
    }
}
