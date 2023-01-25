import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public int size() {
        return taskList.size();
    }

    public Task get(int index) {
        return this.taskList.get(index);
    }

    public Task delete(int index) {
        return this.taskList.remove(index - 1);
    }

    public void markDone(int index) {
        Task task = this.taskList.get(index - 1);
        task.markAsDone();
    }

    public void markUndone(int index) {
        Task task = this.taskList.get(index - 1);
        task.markAsUndone();
    }

    public boolean isEmpty() {
        return (this.size() == 0);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("List of Tasks: \n");
        for (int i = 0; i < taskList.size(); i++) {
            result.append((i + 1));
            result.append(". ");
            result.append(taskList.get(i).toString());
            result.append("\n");
        }
        return result.toString();
    }
}
