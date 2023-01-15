import java.util.LinkedList;

public class TaskList {
    private LinkedList<String> tasks;

    public TaskList() {
        tasks = new LinkedList<>();
    }

    public void addTask(String task) {
        tasks.add(task);
    }

    public void deleteTask(String task) {
        // no implementation yet
    }

    public void printList() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }
}
