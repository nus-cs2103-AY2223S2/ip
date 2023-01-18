import java.util.*;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public String toggleMark(int idx) {
        if (0 <= idx && idx < tasks.size()) {
            Task task = tasks.get(idx);
            task.markTask();
            return task.getStatusIcon();
        }
        return ""; // invalid case, returns empty string for now
    }

    public String toggleUnmark(int idx) {
        if (0 <= idx && idx < tasks.size()) {
            Task task = tasks.get(idx);
            task.unmarkTask();
            return task.getStatusIcon();
        }
        return ""; // invalid case, returns empty string for now
    }

    public String listTasks() {
        StringBuilder display = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            Task curr = this.tasks.get(i);
            display.append((i+1) + ". " + curr.getStatusIcon());
            if (i < this.tasks.size()-1) {
                display.append("\n");
            }
        }
        return display.toString();
    }
}