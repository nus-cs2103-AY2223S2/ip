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
}
