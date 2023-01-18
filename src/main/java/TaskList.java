import java.util.*;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public String listTasks() {
        StringBuilder display = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            Task curr = this.tasks.get(i);
            display.append(i + ". " + curr.getDescription());
            if (i < this.tasks.size()-1) {
                display.append("\n");
            }
        }
        return display.toString();
    }
}