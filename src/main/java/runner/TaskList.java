package runner;
import components.Task;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    protected List<Task> task_list;

    public TaskList() {
        this.task_list = new ArrayList<>();
    }

    public Task get(int n) {
        return task_list.get(n);
    }

    public int size() {
        return task_list.size();
    }

    public void add(Task tk) {
        task_list.add(tk);
    }

    public void remove(int n) {
        task_list.remove(n);
    }

    public void showList() {
        System.out.println("Here are the tasks in your list:");
        for (Task tk : task_list) {
            System.out.println( (task_list.indexOf(tk)+1) + "." + tk.toString());
        }
    }
}
