import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<Task> list = new ArrayList<>();

    public void add(Task task) {
        list.add(task);
    }

    public boolean isValidNumber(int number) {
        int index = getIndexFromNumber(number);
        return index < list.size() && index >= 0;
    }

    public void markTaskAsDone(int number) {
        list.get(getIndexFromNumber(number)).setDone(true);
    }

    public void markTaskAsNotDone(int number) {
        list.get(getIndexFromNumber(number)).setDone(false);
    }

    public void remove(int number) {
        list.remove(getIndexFromNumber(number));
    }

    public String getTaskString(int number) {
        return list.get(getIndexFromNumber(number)).toString();
    }

    private int getIndexFromNumber(int number) {
        return number - 1;
    }

    private int getNumberFromIndex(int index) {
        return index + 1;
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
