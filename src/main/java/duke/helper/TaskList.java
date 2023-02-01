package duke.helper;

import java.util.ArrayList;

import duke.task.Task;

/**
 * TaskList class that handles all the tasks
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private Ui ui;

    /**
     * Constructor of the TaskList class
     *
     * @param tasks tasks to be stored
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.ui = new Ui();
    }

    /**
     * Adds the task to the task list
     *
     * @param task task to be added
     */
    public void addToTasks(Task task) {
        tasks.add(task);
    }

    /**
     * Prints all the tasks stored in the list
     */
    public void outputList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    /**
     * Deletes the task from the tasklist
     *
     * @param taskNo Task number to be deleted
     * @return task that has been deleted
     */
    public Task deleteTask(int taskNo) {
        Task task = tasks.get(taskNo);
        tasks.remove(taskNo);
        ui.showDelete(task, tasks.size());
        return task;
    }

    /**
     * Mark or unmark a task
     *
     * @param isDone whether the task is done
     * @param taskId id of the task
     */
    public void mark(boolean isDone, String taskId) {
        int taskNo = Integer.parseInt(taskId) - 1;
        Task taskToMark = tasks.get(taskNo);
        taskToMark.setIsDone(isDone);
        ui.showMark(isDone, taskToMark);
    }

    /**
     * Outputs the tasklist stored
     *
     * @return tasklist stored
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
