package duke.task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<GeneralDukeTask> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public void addTask(GeneralDukeTask task) {
        list.add(task);
    }

    public int remainingTasks() {
        return this.list.size();
    }

    public GeneralDukeTask getTask(int i) {
        return this.list.get(i);
    }
}
