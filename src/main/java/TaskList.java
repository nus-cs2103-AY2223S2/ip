import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<Task> list = new ArrayList<>();

    public void addTask(String text) {
        this.list.add(new Task(text));
    }

    @Override
    public String toString() {
        String res = "";
        for (int i = 1; i <= this.list.size(); i++) {
            res += i + ". " + this.list.get(i - 1) + "\n";
        }
        return res;
    }
}
