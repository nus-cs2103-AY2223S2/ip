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
    public void list() {
        int curr = 1;
        Iterator<Task> iter = this.tasks.iterator();
        while (iter.hasNext()) {
            Task task = iter.next();
            System.out.println(curr + ". " + task);
            curr++;
        }
    }

    /**
     * Prints the user's tasks that matches keyword in list form.
     */
    public void findTask(String keyword) {
        int curr = 1;
        Iterator<Task> iter = this.tasks.iterator();
        while (iter.hasNext()) {
            Task task = iter.next();
            if (task.getName().contains(keyword)) {
                System.out.println(curr + ". " + task);
                curr++;
            }
        }
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
    public void deleteTask(int i) {
        Task t = this.tasks.remove(i);
        count--;
        System.out.println("Deleted task:\n  " + t + "\nNumber of tasks: " + count);
    }

    /**
     * Marks the given task as completed.
     * @param i Index of task to be marked.
     */
    public void markTask(int i) {
        Task t = this.tasks.get(i);
        t.mark();
        System.out.println("Task has been marked as done:\n " + t);
    }

    /**
     * Unmarks the given task as completed.
     * @param i Index of task to be unmarked.
     */
    public void unmarkTask(int i) {
        Task t = this.tasks.get(i);
        t.unmark();
        System.out.println("Task has been marked as not done:\n " + t);
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
}
