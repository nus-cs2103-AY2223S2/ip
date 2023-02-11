package duke.task;

import duke.task.Task;

import java.util.ArrayList;
import java.util.stream.Stream;


/**
 * TaskList class that helps to handle the arrayList operations
 */
public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<Task>();
    }
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public Task get(int index) {
        return this.list.get(index);
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    public void add(Task t) {
        this.list.add(t);
    }

    public void remove(int index) {
        this.list.remove(index);
    }

    public void mark(int index) {
        this.list.get(index).setMark(true);
    }

    public int size() {
        return this.list.size();
    }

    public Stream<Task> stream() {
        return this.list.stream();
    }

    public int indexOf(String value) {
        return this.list.indexOf(value);
    }

    public void clear() {
        this.list.clear();
    }
}
