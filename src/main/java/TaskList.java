import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> taskList;
    private int totalTasks;

    public TaskList() {
        this.taskList = new ArrayList<>();
        this.totalTasks = 0;
    }

    public boolean addTask(String task) {
        return this.taskList.add(new Task(task, ++totalTasks));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Task t: taskList) {
            sb.append(Utilities.space()).append(t.toString()).append("\n");
        }
        sb.delete(sb.length()-1, sb.length());
        return sb.toString();
    }
}
