package duke;

import static java.util.stream.Collectors.toCollection;

import java.util.ArrayList;
import java.util.Collections;

import duke.task.Task;


/**
 * Creates a new task list.
 *
 * @author Evan Lee
 * @version CS2103 AY22/23 Semester 2
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private boolean isInitializingData = true;

    /** A public constructor to initialize TaskList instance. */
    TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * A public constructor to initialize TaskList instance.
     *
     * @param tasks A list of tasks.
     * */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /** Adds new task to task list and outputs success message. */
    protected void finishInitialization() {
        assert this.isInitializingData : "Duke has already been initialized.";
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
     * @return Success message.
     */
    protected String addTask(Task task) {
        this.tasks.add(task);

        if (this.isInitializingData) {
            return "initializing";
        }

        assert this.tasks.size() != 0 : "Task list is empty.";

        return Ui.addTaskMsg(task, this.tasks.size());
    }

    /**
     * Removes task from task list and outputs success message.
     *
     * @param taskIndex Index of task to be removed.
     * @return Success message.
     */
    protected String removeTask(int taskIndex) {
        Task task = this.tasks.remove(taskIndex);
        return Ui.removeTaskMsg(task, this.tasks.size());
    }

    /**
     * Outputs all the tasks stored in task list.
     *
     * @return List of tasks message.
     */
    protected String listTasks() {
        String listOfTasks = "";

        if (tasks.size() == 0) {
            return "No tasks found!";
        }

        for (int idx = 0; idx < tasks.size(); idx++) {
            Task task = this.tasks.get(idx);
            listOfTasks = listOfTasks + "  " + (idx + 1) + "." + task + "\n";
        }

        return Ui.listTasksMsg(listOfTasks);
    }

    /**
     * Marks task as completed and outputs success message.
     *
     * @param task Task to be marked.
     * @return Success message.
     */
    protected String markTask(Task task) {
        task.mark();
        return Ui.markTaskMsg(task);
    }

    /**
     * Marks task as uncompleted and outputs success message.
     *
     * @param task Task to be unmarked.
     * @return Success message.
     */
    protected String unmarkTask(Task task) {
        task.unmark();
        return Ui.unmarkTaskMsg(task);
    }

    /**
     * Filters task list by a given query.
     *
     * @param query Query that will be used to filter task list.
     * @return Filtered task list.
     */
    protected ArrayList<Task> filteredTaskList(String query) {
        return tasks.stream()
                .filter(task -> task.toString().contains(query))
                .collect(toCollection(ArrayList::new));
    }

    /**
     * Sorts task list by task name.
     *
     * @return Success message.
     */
    protected String sort() {
        Collections.sort(tasks, (task1, task2) -> task1.getTaskName().compareTo(task2.getTaskName()));

        return Ui.sortedMessage();
    }
}
