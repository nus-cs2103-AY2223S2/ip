import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void addTask(Task currentTask) {
        this.taskList.add(currentTask);
    }

    public Task deleteTask(int taskPosition) {
        return this.taskList.remove(taskPosition - 1);
    }

    public Task getTask(int pos) {
        return this.taskList.get(pos);
    }
    public int size() {
        return this.taskList.size();
    }
}
