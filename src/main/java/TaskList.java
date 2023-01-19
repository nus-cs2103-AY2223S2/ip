import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.taskList.add(task);
        System.out.println("Added task: " + task);
    }

    public int size() {
        return taskList.size();
    }

    public Task get(int index) {
        return taskList.get(index);
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
