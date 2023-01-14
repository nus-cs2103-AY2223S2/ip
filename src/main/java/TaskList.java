import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public void addTask(Task t) {
        this.list.add(t);
        System.out.println("Got it. I've added this task: ");
        t.printStatus();
        printCount();
    }

    public Task getTask(int index) {
        return this.list.get(index);
    }

    public int count() {
        return this.list.size();
    }

    public void printCount() {
        System.out.println("Now you have "+count()+" task(s) in the list.");
    }
}
