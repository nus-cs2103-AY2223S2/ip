package duke.task;

import java.util.ArrayList;

/**
 * Stores a list of tasks.
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

    /**
     * Sorts the task list.
     * @return The sorted task list.
     */
    public ArrayList<Task> sort() {
        ArrayList<Task> sortedTasks = new ArrayList<>(taskList);
        sortedTasks.sort(Task::compareTo);
        return sortedTasks;
    }

    /**
     * Finds tasks that contain the keyword.
     * @param keyword The keyword to search for.
     * @return The list of tasks that contain the keyword.
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getTask().contains(keyword)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
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
