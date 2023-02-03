package TaskList;
import java.util.ArrayList;
import Task.Task;
public class TaskList {
    ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    public Task getTask(int index) {
        return this.list.get(index);
    }

    public void removeTask(int index) {
        this.list.remove(index);
    }

    public void addTask(Task task) {
        this.list.add(task);
    }

    public int size() {
        return this.list.size();
    }


}
