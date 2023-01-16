import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<Task> list = new ArrayList<>();

    public void addTask(String text) {
        list.add(new ToDoTask(text));
    }


    public void markTaskAsDone(int number) {
        list.get(getIndexFromNumber(number)).setDone(true);
    }

    public void markTaskAsNotDone(int number) {
        list.get(getIndexFromNumber(number)).setDone(false);
    }

    public String getTaskString(int number) {
        return list.get(getIndexFromNumber(number)).toString();
    }

    private int getIndexFromNumber(int number) {
        return number - 1;
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
