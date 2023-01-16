import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<Task> list = new ArrayList<>();

    public void addTask(String text) {
        list.add(new Task(text));
    }

    @Override
    public String toString() {
        String res = "";
        for (int i = 1; i <= list.size(); i++) {
            res += i + ". " + list.get(i - 1);
            if (i < list.size()) {
                res += "\n";
            }
        }
        return res;
    }
}
