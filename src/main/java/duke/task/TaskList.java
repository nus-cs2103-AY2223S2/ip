package duke.task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<DukeTask> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public void addTask(DukeTask task) {
        this.list.add(task);
    }

    public void deleteTask(int taskIndex) {
        this.list.remove(taskIndex);
    }

    public int remainingTasks() {
        return this.list.size();
    }

    public DukeTask getTask(int i) {
        return this.list.get(i);
    }

    @Override
    public String toString() {
        StringBuilder listContent = new StringBuilder();
        for (int i = 0; i < this.remainingTasks(); i++) {
            listContent.append(i + 1).append(".").append(this.getTask(i)).append("\n");
        }
        return String.valueOf(listContent);
    }
}
