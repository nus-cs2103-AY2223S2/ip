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

    public Task remove(int taskId) {
        return this.tasks.remove(taskId - 1);
    }

    public Task getLastTask() {
        return this.tasks.get(this.tasks.size() - 1);
    }

    public int getSize() {
        return this.tasks.size();
    }

    public void mark(int taskId) {
        this.tasks.get(taskId - 1).complete();
    }

    public void unmark(int taskId) {
        this.tasks.get(taskId - 1).uncomplete();
    }

    public List<Task> getList() {
        return this.tasks;
    }
}
