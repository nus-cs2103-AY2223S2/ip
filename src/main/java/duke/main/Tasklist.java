package duke.main;

import duke.task.Task;
import java.util.ArrayList;

/**
 * Tasklist class holds the tasks added and deleted in a session
 */
public class Tasklist {
    private final ArrayList<Task> tasks;

    /**
     * constructor to create a tasklist to stores tasks
     */
    public Tasklist() {
        this.tasks = new ArrayList<>();
    }

    /**
     * constructor to create a tasklist from data in the saved file
     * @param tasks arraylist of tasks data from saved file
     */
    public Tasklist(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * getter method for arraylist of tasks
     * @return arraylist of tasks
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public int getTasksNum() {
        return this.tasks.size();
    }

    /**
     * adder method to add a task
     * @param task task to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
    }
    /**
     *  method to delete a task
     * @param taskNum task index to be added
     */
    public void deleteTask(int taskNum) {
        tasks.remove(taskNum);
    }
    /**
     *  method to mark a task as done
     * @param taskNum task index to be marked as done
     */
    public void markDone(int taskNum) {
        tasks.get(taskNum).markDone();
    }
    /**
     *  method to mark a task as not done
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
