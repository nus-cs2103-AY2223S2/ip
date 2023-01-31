import java.util.ArrayList;
import java.util.Arrays;

public class TaskList {
    ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }
    public TaskList(Task[] inputTasks) {
        this();
        tasks.addAll(Arrays.asList(inputTasks));
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void remove(int i) {
        tasks.remove(i);
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public int size() {
        return tasks.size();
    }

    public Task[] asList() {
        return tasks.toArray(new Task[0]);
    }
}
