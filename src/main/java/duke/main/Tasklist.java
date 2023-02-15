package duke.main;

import duke.task.Task;
import java.util.ArrayList;

/**
 * Tasklist class to hold the tasks added and deleted in a session
 */
public class Tasklist {
    private final ArrayList<Task> tasks;

    /**
     * creates a tasklist to stores tasks
     */
    public Tasklist() {
        this.tasks = new ArrayList<>();
    }

    /**
     * creates a tasklist from data in the saved file
     * @param tasks arraylist of tasks data from saved file
     */
    public Tasklist(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * gets arraylist of tasks
     *
     * @return arraylist of tasks
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     *  gets the size of current tasklist
     * @return size of tasklist in int
     */
    public int getTasksNum() {
        return this.tasks.size();
    }
    /**
     * adds a task to existing tasks
     *
     * @param task task to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
    }
    /**
     *  deletes a task from existing tasks
     *
     * @param taskNum task index to be added
     */
    public void deleteTask(int taskNum) {
        tasks.remove(taskNum);
    }
    /**
     * marks a task as done
     *
     * @param taskNum task index to be marked as done
     */
    public void markDone(int taskNum) {
        tasks.get(taskNum).markDone();
    }
    /**
     * marks a task as not done
     *
     * @param taskNum task index to be marked as not done
     */
    public void markUndone(int taskNum) {
        tasks.get(taskNum).markUndone();
    }

    public ArrayList<Task> findTask(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : this.tasks) {
            String taskDescription = task.getDescription();
            if (taskDescription.contains(keyword)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }
}
