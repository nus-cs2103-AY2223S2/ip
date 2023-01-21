package task;

import java.util.ArrayList;
import java.util.Iterator;

public class TaskList implements Iterable<Task> {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }
    public void add(Task task) {
        tasks.add(task);
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public void remove(Task task) {
        tasks.remove(task);
    }
    public void save() {

    }
    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }
}
