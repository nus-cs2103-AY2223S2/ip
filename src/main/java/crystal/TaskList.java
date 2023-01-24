package crystal;

import crystal.task.Task;
import java.util.ArrayList;

public class TaskList {

    public ArrayList<Task> arr;
    public TaskList(ArrayList<Task> arr) throws CrystalException{
        this.arr = arr;
    }

    public TaskList() {
        this.arr = arr;
    }

    public int size() {
        return arr.size();
    }

    public Task get(int i) {
        return this.arr.get(i);
    }

    public void add(Task t) {
        this.arr.add(t);
    }

    public void remove(int i) {
        this.arr.remove(i);
    }
}
