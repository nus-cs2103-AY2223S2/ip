import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskStore;

    public TaskList() {
        ArrayList<Task> taskStore = new ArrayList<>();
    }
    public int countTasks() {
        return this.taskStore.size();
    }
    public Task getTaskAtIndex(int index) {
        return taskStore.get(index);
    }
    public void addTask(Task t) {
        taskStore.add(t);
    }
}
