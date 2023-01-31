import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;


    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public Task remove(int taskId) {
        return this.tasks.remove(taskId - 1);
    }

    public Task get(int taskId) {
        return this.tasks.get(taskId - 1);
    }
    public Task getLastTask() {
        return this.tasks.get(this.tasks.size() - 1);
    }

    public int getSize() {
        return this.tasks.size();
    }

    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    public Task mark(int taskId) {
        Task taskToMark = this.get(taskId);
        taskToMark.complete();
        return taskToMark;
    }

    public Task unmark(int taskId) {
        Task taskToUnmark = this.get(taskId);
        taskToUnmark.uncomplete();
        return taskToUnmark;
    }

    public List<Task> getList() {
        return this.tasks;
    }
}
