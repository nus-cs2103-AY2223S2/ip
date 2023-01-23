package duke.tasks;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasks = new ArrayList<>();

    public String outputList() {
        StringBuilder result = new StringBuilder();
        for (int index = 0; index < this.tasks.size(); index++) {
            result.append((index == 0 ? "" : "\n") + (index + 1) + ". " + this.tasks.get(index).toString());
        }
        return result.toString();
    }

    public int countTasks() {
        return this.tasks.size();
    }

    public Task removeTask(int index) {
        return this.tasks.remove(index);
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }

}
