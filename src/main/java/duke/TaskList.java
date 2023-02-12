package duke;

import java.util.ArrayList;

import duke.exceptions.EmptyDescriptionException;
import duke.tasks.Task;

public class TaskList extends ArrayList<Task> {
    public TaskList() {
        super();
    }

    public void addTask(Task task) {
        this.add(task);
    }

    public Task deleteTask(int taskNumber) throws EmptyDescriptionException {
        Task currentTask = this.get(taskNumber - 1);
        this.remove(taskNumber - 1);
        return currentTask;
    }
}
