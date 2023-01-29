package duke.tasklist;

import java.util.ArrayList;

import duke.tasktypes.Task;
import duke.ui.Ui;


/**
 * Represents a collection of tasks.
 * A TaskList object corresponds to User's task collection represented by an ArrayList.
 */
public class TaskList {
    private ArrayList<Task> taskStorage;
    private int numTasks;
    private Ui ui;

    /**
     * Constructs a TaskList collection.
     * TaskList object is constructed with an Ui instance.
     * Ui object assists TaskList in communicating with User.
     *
     * @param ui Ui instance.
     */
    public TaskList(Ui ui) {
        taskStorage = new ArrayList<>();
        this.numTasks = 0;
        this.ui = ui;
    }

    /**
     * Adds task to collection.
     * Does not produce output to User.
     *
     * @param task Task instance to be added.
     */
    public void loadTask(Task task) {
        this.taskStorage.add(task);
        numTasks++;
    }

    /**
     * Adds task to collection.
     * Informs User upon successful task addition.
     *
     * @param task Task instance to be added.
     */
    public void addTask(Task task) {
        this.taskStorage.add(task);
        numTasks++;
        ui.taskAdd(task, numTasks);
    }

    /**
     * Deletes task from collection.
     * Informs User upon successful task deletion.
     *
     * @param toDelete Integer index of task to be deleted.
     */
    public void deleteTask(int toDelete) {
        Task deleted = taskStorage.remove(toDelete - 1);
        numTasks--;
        ui.taskDelete(deleted, numTasks);
    }

    /**
     * Marks specified task in collection as complete.
     * Informs User upon successful operation.
     *
     * @param mark Integer index of task to be mark complete.
     */
    public void markTask(int mark) {
        Task marked = taskStorage.get(mark - 1);
        marked.setDone();
        ui.markTaskDone(marked);
    }

    /**
     * Unmarks specified task in collection as incomplete.
     * Informs User upon successful operation.
     *
     * @param unmark Integer index of task to be mark incomplete.
     */
    public void unmarkTask(int unmark) {
        Task unmarked = taskStorage.get(unmark - 1);
        unmarked.setUndone();
        ui.markTaskUndone(unmarked);
    }

    /**
     * Prints all current tasks in collection to standard output.
     */
    public void printTasks() {
        int count = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task task : taskStorage) {
            String output = String.format("%d.%s", count++, task.toString());
            System.out.println(output);
        }
    }

    /**
     * Returns a new TaskList object containing tasks that matched given keyword.
     *
     * @param keyword String keyword to be matched with task descriptions.
     * @return Returns a new TaskList object containing matching tasks.
     */
    public TaskList getMatchingTasks(String keyword) {
        TaskList matchTasks = new TaskList(this.ui);
        for (Task task : this.taskStorage) {
            if (task.matchKeyword(keyword)) {
                matchTasks.loadTask(task);
            }
        }
        return matchTasks;
    }

    /**
     * Returns ArrayList representation of task collection.
     *
     * @return ArrayList containing all tasks.
     */
    public ArrayList<Task> getTasks() {
        return taskStorage;
    }

    /**
     * Returns the number of tasks in collection.
     *
     * @return Number of tasks in collection.
     */
    public int getNumTasks() {
        return numTasks;
    }

}
