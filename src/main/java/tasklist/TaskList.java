package tasklist;

import java.io.Serializable;
import java.util.ArrayList;

import tasklist.task_types.Task;

public class TaskList implements Serializable {
    private ArrayList<Task> list = new ArrayList<>();

    public void addTask(Task task) {
        list.add(task);
    }

    public Task getTask(int index) {
        return list.get(index);
    }

    public void deleteTask(int index) {
        list.remove(index);
    }

    public void markedTask(int index) {
        list.get(index).setStatus(true);
    }

    public void unmarkedTask(int index) {
        list.get(index).setStatus(false);
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < list.size(); i++) {
            int index = i + 1;
            System.out.println("\n" + index + ". " + list.get(i).toString());
        }

        return str;
    }

    public String getTotal() {
        return String.format("Now you have %d tasks in the list", list.size());
    }
}
