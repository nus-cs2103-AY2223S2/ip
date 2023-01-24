import java.io.Serializable;
import java.util.ArrayList;
public class TaskList implements Serializable {
    private ArrayList<Task> lst;

    public TaskList() {
        this.lst = new ArrayList<>();
    }

    public Task get(int n) {
        return this.lst.get(n);
    }

    public void add(Task task) {
        this.lst.add(task);
    }

    public int size() {
        return this.lst.size();
    }

    public void remove(int n) {
        this.lst.remove(n);
    }

    @Override
    public String toString() {
        return this.lst.toString();
    }
}
