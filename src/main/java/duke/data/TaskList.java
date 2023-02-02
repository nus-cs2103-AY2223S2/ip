package duke.data;
import java.io.Serializable;
import java.util.List;

import duke.action.Task;

public class TaskList implements Serializable {
    private List<Task> list;

    public TaskList(List<Task> list) {
        this.list = list;
    }

    public List<Task> getTasks() {
        return this.list;
    }

    public Task getTaskByIndex(int index) {
        return this.list.get(index);
    }

    // CRUD methods here for task list
    public void addTask(Task Task) {
        list.add(Task);
    }

    public void removeTask(int taskIndex) {
        list.remove(taskIndex);
    }

    public void markTask(int taskIndex) {
        Task currentTask = this.list.get(taskIndex);
        currentTask.markAsDone();
    }

    public void unmarkTask(int taskIndex) {
        Task currentTask = this.list.get(taskIndex);
        currentTask.unmarkAsDone();
    }

    public int getSize() {
        return this.list.size();
    }
}
