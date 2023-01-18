import java.util.LinkedList;
import java.util.List;

public class TaskList {
    private List<String> tasks;

    TaskList() {
        this.tasks = new LinkedList<>();
    }

    public void addTask(String task) {
        this.tasks.add(task);
    }

    @Override
    public String toString() {
        String result = "";
        Integer curr = 1;

        for (String s: tasks) {
            result += curr.toString() + ". " + s + '\n';
            curr++;
        }

        return result;
    }
}
