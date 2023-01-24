package tasks;

import java.util.*;
import static ui.Ui.LS;

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
    public String numTasksMsg() {
        return "Now you have " + this.tasks.size() + " tasks in the list.";
    }
    public String formatList() {
        String formattedList = "";
        for (Object t : this.tasks) {
            int pos = this.tasks.indexOf(t) + 1;
            formattedList += pos + ". " + t + LS;
        }
        return formattedList.trim();
    }
    public String toText(int i) {
        Task t = tasks.get(i);
        return t.toText();
    }
    public String toString(int i) {
        Task t = tasks.get(i);
        return t.toString();
    }
}
