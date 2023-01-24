import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> list = new ArrayList<>();

    public TaskList(ArrayList<Task> loadList) {
        this.list.addAll(loadList);
    }

    public int listLength() {
        return this.list.size();
    }
    public void addTask(Task task) {
        this.list.add(task);
    }

    public void deleteTask(int taskIndex) {
        this.list.remove(taskIndex - 1);
    }

    public Task getTask(int taskIndex) {
        return this.list.get(taskIndex - 1);
    }

    public boolean isEmpty() {
        return this.list.isEmpty();
    }


}
