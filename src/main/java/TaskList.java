import java.util.ArrayList;

public class TaskList {
    protected int init;
    ArrayList<Task> list = new ArrayList<>(init);

    public TaskList(int init) {
        this.init = init;
    }

    public void addTask(Task t) {
        this.list.add(t);
    }

    public int getSize() {
        return this.list.size();
    }

    public Task getTask(int index) {
        return this.list.get(index);
    }

    public void deleteTask(int index) {
        this.list.remove(index);
    }


}
