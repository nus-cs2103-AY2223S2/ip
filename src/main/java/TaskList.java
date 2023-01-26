package src.main.java;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private boolean isInitializingData = true;

    /** A public constructor to initialize TaskList instance. */
    TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /** Adds new task to task list and outputs success message. */
    protected void finishInitialization() {
        this.isInitializingData = false;
    }

    /** 
     * Gets specific task.
     * 
     * @param taskIndex The index of the task.
     */
    protected Task getTask(int taskIndex) {
        return this.tasks.get(taskIndex);
    }

    /** 
     * Gets list of tasks.
     * 
     * @return Task list.
     */
    protected ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    /** 
     * Adds new task to task list and outputs success message.
     * 
     * @param task Task to be added to task list.
     */
    protected void addTask(Task task) {
        this.tasks.add(task);

        if (this.isInitializingData) {
            return;
        }

        Ui.addTaskMsg(task, this.tasks.size());
    }

    /** 
     * Removes task from task list and outputs success message.
     * 
     * @param taskIndex Index of task to be removed.
     */
    protected void removeTask(int taskIndex) {
        Task task = this.tasks.remove(taskIndex);
        Ui.removeTaskMsg(task, this.tasks.size());

    }

    /** Outputs all the tasks stored in task list. */
    protected void listTasks() {
        String listOfTasks = "";

        for(int idx = 0; idx < tasks.size(); idx++) {
            Task task = this.tasks.get(idx);
            listOfTasks = listOfTasks + "  " + (idx + 1) + "." + task + "\n";
        }

        Ui.listTasksMsg(listOfTasks);
    }

    /** 
     * Marks task as completed and outputs success message.
     * 
     * @param task Task to be marked.
     */
    protected void markTask(Task task) {
        task.mark();
        Ui.markTaskMsg(task);
    }

    /** 
     * Marks task as uncompleted and outputs success message.
     * 
     * @param task Task to be unmarked.
     */
    protected void unmarkTask(Task task) {
        task.unmark();
        Ui.unmarkTaskMsg(task);
    }
}