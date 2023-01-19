import java.util.ArrayList;
import java.util.stream.Stream;

public class TaskList {
    private ArrayList<Task> tasks;
    private int nextId;

    public TaskList() {
        tasks = new ArrayList<>();
        nextId = 1;
    }

    public int size() {
        return tasks.size();
    }

    public void add(Task t) {
        tasks.add(t);
    }

    public Task remove (Task t) {
        tasks.remove(t);
        return t;
    }

    public int nextId() {
        return nextId++;
    }

    public Stream<Task> stream() {
        return tasks.stream();
    }
}
