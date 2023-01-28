import java.util.ArrayList;
import java.util.List;

public class TaskList {

    List<Task> list;

    public TaskList() {
        list = new ArrayList<>();
    }

    public void addTask(Task toAdd) {
        list.add(toAdd);
    }

    public Task getAtIndex(int i) {
        return list.get(i);
    }

    public int getSize() {
        return list.size();
    }

    public void removeAtIndex(int i) {
        list.remove(i);
    }
}
