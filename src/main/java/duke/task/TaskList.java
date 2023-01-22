package duke.task;

import java.util.ArrayList;

/**
 * The task list class.
 * Contains the task list and operations to modify the task list.
 */
public class TaskList {
    private final ArrayList<Task> taskList = new ArrayList<>();

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void markTask(int index) {
        taskList.get(index).markAsDone();
    }

    public void unmarkTask(int index) {
        taskList.get(index).markAsNotDone();
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public int getSize() {
        return taskList.size();
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public Task deleteTask(int index) {
        return taskList.remove(index);
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < taskList.size(); i++) {
            output += (i + 1) + ". " + taskList.get(i) + "\n";
        }
        return output;
    }
}
