import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();

    public TaskList (ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList () {
        this.taskList = new ArrayList<>();
    }

    protected Task getTaskAt (int index) {
        return taskList.get(index);
    }

    protected void setTaskAt (int index, Task task) {
        taskList.set(index, task);
    }

    protected void add(Task task) {
        taskList.add(task);
    }

    protected void removeTaskAt (int index) {
        taskList.remove(index);
    }

    protected int size() {
        return taskList.size();
    }
}
