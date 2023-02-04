package tunabot;

import java.util.ArrayList;

import tunabot.task.Task;


/**
 * Class to handle TaskList
 */
public class TaskList {
    private final ArrayList<Task> tasks;
    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    public void add(Task t) {
        tasks.add(t);
    }
    public int size() {
        return tasks.size();
    }

    public Task get(int i) {
        return tasks.get(i);
    }
    public void remove(int i) {
        tasks.remove(i);
    }

    /**
     * Returns a TaskList containing tasks that contain the given target String
     * @param target Target string to search for
     * @return targetList TaskList containing matching tasks
     */
    public TaskList find(String target) {
        TaskList targetList = new TaskList();
        for (Task task : tasks) {
            if (task.getName().contains(target)) {
                targetList.add(task);
            }
        }
        return targetList;
    }

    public void set(int index, Task updateTask) {
        tasks.set(index, updateTask);
    }
}
