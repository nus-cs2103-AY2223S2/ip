package Duke;

import Duke.Commands.Tasks.Task;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void add(Task newTask) {
        this.tasks.add(newTask);
    }

    public void remove(int index) {
        this.tasks.remove(index);
    }

    public int size() {
        return this.tasks.size();
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }

    public String toString() {
        String res = "";
        for (int i = 0; i < tasks.size(); i++) {
            res += String.format("%d.%s\n", i + 1, tasks.get(i));
        }
        return res;
    }
}
