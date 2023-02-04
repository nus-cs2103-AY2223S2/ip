import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasklist;

    public TaskList() {
        this.tasklist = new ArrayList<>();
    }

    public int size() {
        return tasklist.size();
    }

    public Task get(int index) {
        return tasklist.get(index);
    }

    public void add(Task task) {
        tasklist.add(task);
    }

    public Task remove(int taskindex) {
        return tasklist.remove(taskindex);
    }

    public boolean isEmpty() {
        return tasklist.isEmpty();
    }
}
