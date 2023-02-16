package duke;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is a TaskList that keeps track of
 * user's list of tasks.
 */
public class TaskList {

    private List<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public Task getTask(int index) {

        return taskList.get(index);
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void removeTask(int index) {
        taskList.remove(index);
    }

    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    public int getArraySize() {
        return taskList.size();
    }

}
