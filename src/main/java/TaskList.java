import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public Task remove(int taskNum) {
        return this.tasks.remove(taskNum - 1);
    }

    public List<Task> getList() {
        return this.tasks;
    }
}
