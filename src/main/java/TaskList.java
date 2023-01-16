import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<Task> list = new ArrayList<>();

    public void addTask(String text) {
        list.add(new Task(text));
    }


    public void markTaskAsDone(int number) {
        int index = number - 1;
        list.get(index).setDone(true);
    }

    public void markTaskAsNotDone(int number) {
        int index = number - 1;
        list.get(index).setDone(false);
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
