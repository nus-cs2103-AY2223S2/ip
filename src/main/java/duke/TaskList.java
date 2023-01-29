package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskStore;

    public TaskList() {
        this.taskStore = new ArrayList<>();
    }

    /**
     * Gets the number of tasks in a TaskList.
     * @return number of tasks
     */
    public int countTasks() {
        return this.taskStore.size();
    }
    public Task getTaskAtIndex(int index) {
        return this.taskStore.get(index);
    }
    public void addTask(Task t) {
        this.taskStore.add(t);
    }
    public void deleteTask(int index) {
        this.taskStore.remove(index - 1);
    }
}
