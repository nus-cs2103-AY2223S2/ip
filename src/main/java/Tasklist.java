import java.lang.reflect.Array;
import java.util.ArrayList;

public class Tasklist {
    ArrayList<Task> list;

    public Tasklist() {
        this.list = list = new ArrayList<Task>(100);
    }

    public void add(Task task) {
        list.add(task);
    }

    public void delete(int i) {
        list.remove(i);
    }

    public Task get(int i) {
        return list.get(i);
    }

    public int size() {
        return list.size();
    }
}
