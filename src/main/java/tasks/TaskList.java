package tasks;

import java.util.*;
import static ui.Ui.LS;

/**
 * List of Tasks.
 */
public class TaskList {
    private List<Task> tasks;
    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    public void addTask(Task t) {
        tasks.add(t);
    }
    public void removeTask(int i) {
        tasks.remove(i);
    }
    public void markTask(int i) {
        Task t = tasks.get(i);
        t.markAsDone();
    }
    public void unmarkTask(int i) {
        Task t = tasks.get(i);
        t.unmarkAsDone();
    }

    /**
     * Prints list of task in specified format.
     */
    public String formatList() {
        String formattedList = "";
        for (Object t : this.tasks) {
            int pos = this.tasks.indexOf(t) + 1;
            formattedList += pos + ". " + t + LS;
        }
        return formattedList.trim();
    }
    public String numTasksMsg() {
        return "Now you have " + this.tasks.size() + " tasks in the list.";
    }

    /**
     * Returns text representation of task at particular index.
     * @param i Index position of task.
     * @return Text representation string of task at particular index.
     */
    public String toText(int i) {
        Task t = tasks.get(i);
        return t.toText();
    }

    /**
     * Returns string representation of task at particular index.
     * @param i Index position of task.
     * @return String representation of task at particular index.
     */
    public String toString(int i) {
        Task t = tasks.get(i);
        return t.toString();
    }
}
