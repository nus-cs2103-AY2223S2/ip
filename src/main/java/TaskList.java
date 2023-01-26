import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList(int size) {
        list = new ArrayList<Task>(size);
    }

    public void addTask(Task task) {
        list.add(task);
    }

    public int getSize() {
        return list.size();
    }

    public Task getTask(int index) {
        return list.get(index);
    }

    public Task removeTask(int index) {
        return list.remove(index);
    }
}
