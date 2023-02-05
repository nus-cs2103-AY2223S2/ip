package duke.tasks;

import static duke.ui.Ui.LS;

import java.util.ArrayList;
import java.util.List;

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
        assert t != null : "The task being added is invalid!";
        tasks.add(t);
    }
    public void removeTask(int i) {
        tasks.remove(i);
    }

    public boolean isDuplicate(Task newTask) {
        boolean isDup = false;
        for (Task t : this.tasks) {
            if (newTask.toText().equals(t.toText())) {
                isDup = true;
            }
        }
        return isDup;
    }

    /**
     * Mark Task object at index i.
     */
    public void markTask(int i) {
        Task t = tasks.get(i);
        t.markAsDone();
    }

    /**
     * Unmark Task object at index i.
     */
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

    /**
     * Find tasks that match the provided string.
     */
    public String findList(String s) {
        String matchList = "";
        for (Object t : this.tasks) {
            if (t.toString().contains(s)) {
                int pos = this.tasks.indexOf(t) + 1;
                matchList += pos + ". " + t + LS;
            }
        }
        return matchList.trim();
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
