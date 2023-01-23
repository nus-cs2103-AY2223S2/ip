import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public int size() {
        return this.tasks.size();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task delete(int taskNum) {
        return this.tasks.remove(taskNum-1);
    }

    public String toggleMark(int taskNum) {
        Task task = tasks.get(taskNum-1);
        task.markTask();
        return task.getStatusIcon();
    }

    public String toggleUnmark(int taskNum) {
        Task task = tasks.get(taskNum-1);
        task.unmarkTask();
        return task.getStatusIcon();
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