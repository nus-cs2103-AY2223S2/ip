import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> list;
    private int items;

    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    public String addTask(Task task) {
        list.add(task);
        items++;
        return "added: " + task.name;
    }

    public String printTaskList() {
        StringBuilder tasklist = new StringBuilder();
        for(int i = 0; i < items; i++) {
            tasklist.append(i + 1).append(".").append(list.get(i).name).append("\n");
        }
        return tasklist.toString();
    }
}
