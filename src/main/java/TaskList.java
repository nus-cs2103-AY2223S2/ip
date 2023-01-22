import java.io.Serializable;
import java.util.ArrayList;

public class TaskList implements Serializable {
    private ArrayList<Task> list;
    public TaskList() {
        this.list = new ArrayList<Task>();

    }

    public Task get(int i) {
        return this.list.get(i);
    }

    public void add(Task t) {
        this.list.add(t);
    }

    public int size() {
        return this.list.size();
    }

    public void remove(int i) {
        this.list.remove(i);
    }

    @Override
    public String toString() {
        return "" + this.list;
    }

}
