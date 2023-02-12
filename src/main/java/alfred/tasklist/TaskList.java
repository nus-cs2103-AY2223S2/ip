package alfred.tasklist;

import java.util.ArrayList;

import alfred.exception.AlfredException;
import alfred.task.Task;
import alfred.ui.Ui;

/**
 * TaskList stores all tasks and performs operation to manage the tasks
 */
public class TaskList {
    protected ArrayList<Task> taskList;
    private Ui ui;

    /**
     * Constructor for TaskList
     *
     * @param taskList
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
        this.ui = new Ui();
    }

    public int getSize() {
        return this.taskList.size();
    }

    /**
     * Find out number of mark tasks
     *
     * @return Number of mark tasks
     * @throws AlfredException
     */
    public int getSizeOfMarkedTasks() throws AlfredException {
        int sizeOfMarkTasks = 0;
        for (int i = 0; i < this.getSize(); i++) {
            if (this.getTask(i).getStatusIcon().equals("X")) {
                sizeOfMarkTasks = sizeOfMarkTasks + 1;
            }
        }
        return sizeOfMarkTasks;
    }

    /**
     * Find out number of unmark tasks
     *
     * @return Number of unmark tasks
     * @throws AlfredException
     */
    public int getSizeOfUnmarkedTasks() throws AlfredException {
        return this.getSize() - this.getSizeOfMarkedTasks();
    }

    /**
     * Obtain task based on given index
     *
     * @param index
     * @return Task
     */
    public Task getTask(int index) {
        // handle errors out of range
        int display = index + 1;
        assert index >= 0 && index < this.getSize() : "Task" + display + " does not exist.";
        return taskList.get(index);
    }

    /**
     * Marks a task based on the task index provided and throws an exception if task does not exist.
     *
     * @param index
     * @throws AlfredException
     */
    public void markTask(int index) throws AlfredException {
        // handle errors out of range
        int display = index + 1;
        assert index >= 0 && index < this.getSize() : "Task" + display + " does not exist.";
        this.getTask(index).markAsDone();
    }

    /**
     * Unmarks a task based on the task index provided
     *
     * @param index
     * @throws AlfredException
     */
    public void unmarkTask(int index) throws AlfredException {
        // handle errors out of range
        int display = index + 1;
        assert index >= 0 && index < this.getSize() : "Task" + display + " does not exist.";
        this.getTask(index).markAsUndone();
    }

    /**
     * Add task to taskList
     *
     * @param task
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Remove task from taskList
     *
     * @param index
     * @throws AlfredException
     */
    public void removeTask(int index) throws AlfredException {
        // handle errors out of range
        int display = index + 1;
        assert index >= 0 && index < this.getSize() : "Task" + display + " does not exist.";
        taskList.remove(index);
    }

    /**
     * Find and filter task list based on keyword
     *
     * @param keyword
     * @return List of filtered tasks
     * @throws AlfredException
     */
    public TaskList findTask(String keyword) throws AlfredException {
        TaskList filteredTaskList = new TaskList(new ArrayList<>());
        for (int i = 0; i < this.getSize(); i++) {
            if (this.getTask(i).getStorageDetails().contains(keyword)) {
                filteredTaskList.addTask(this.getTask(i));
            }
        }
        return filteredTaskList;
    }

}
