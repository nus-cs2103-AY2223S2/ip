import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<Task> list = new ArrayList<>();

    public Task addTodo(String text) {
        Task task = new ToDoTask(text);
        list.add(task);
        return task;
    }

    public Task addDeadline(String[] textAndDate) {
        Task task = new DeadlineTask(textAndDate[0], textAndDate[1]);
        list.add(task);
        return task;
    }

    public Task addEvent(String[] textAndDate) {
        Task task = new EventTask(textAndDate[0], textAndDate[1], textAndDate[2]);
        list.add(task);
        return task;
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
