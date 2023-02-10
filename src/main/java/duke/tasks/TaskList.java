package duke.tasks;
import java.util.ArrayList;
import java.util.stream.Stream;

public class TaskList implements Iterable<Task> {
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

    public Task get(int i) {
        return tasks.get(i);
    }

    public int nextId() {
        return nextId++;
    }

    public Stream<Task> stream() {
        return tasks.stream();
    }

    @Override public java.util.Iterator<Task> iterator() {
        return tasks.iterator();
    }
}
