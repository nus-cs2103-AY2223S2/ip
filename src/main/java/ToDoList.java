import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ToDoList {
    private final List<String> tasks;

    public ToDoList() {
        this.tasks = new ArrayList<>();
    }

    public String addTask(String task) {
        this.tasks.add(task);
        return String.format("added: %s", task);
    }

    public List<String> getTasksForPrint() {
        if (tasks.isEmpty()) {
            return List.of("No tasks, you're good for the day!");
        }

        List<String> res = new LinkedList<>();
        for (int i = 0; i < tasks.size(); i++) {
            res.add(String.format("%d. %s", i + 1, tasks.get(i)));
        }
        return res;
    }
}
