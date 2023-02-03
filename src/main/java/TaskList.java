import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public void setList(ArrayList<Task> list) {
        this.list = list;
    }
    
    public int size() {
        return this.list.size();
    }

    public void add(Task t) {
        this.list.add(t);
    }

    public Task get(int i) {
        return this.list.get(i);
    }

    public void remove(int i) {
        this.list.remove(i);
    }

    public ArrayList<Task> getList() {
        return this.list;
    }
}
