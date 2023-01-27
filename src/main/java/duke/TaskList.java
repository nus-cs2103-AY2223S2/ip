package duke;
import duke.tasks.Task;
import java.io.Serializable;
import java.util.ArrayList;

public class TaskList implements Serializable {
    private ArrayList<Task> tasks;
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public Task get(int i) {
        return this.tasks.get(i);
    }

    public void add(Task t) {
        this.tasks.add(t);
    }

    public int size() {
        return this.tasks.size();
    }

    public void remove(int i) {
        this.tasks.remove(i);
    }

    @Override
    public String toString() {
        return "" + this.tasks;
    }

}
