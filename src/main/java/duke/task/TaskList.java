package duke.task;

import java.io.IOException;
import java.util.ArrayList;

import duke.storage.Storage;
import duke.ui.Ui;

/**
 * The list of task of user.
 */
public class TaskList {
    private static ArrayList<Task> tasks;

    /**
     * Constructs a TaskList object from an array list.
     *
     * @param tasks Tasks to be tracked.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the task at the 0-based index of the list.
     *
     * @param index The index of the task
     * @return The task at the 0-based index of the list.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Adds a new task to the list.
     *
     * @param task Task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Returns the current size of the list.
     *
     * @return The current size of the list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Removes the task at the 0-based index of the list.
     *
     * @param index The index of the task to be removed.
     */
    public void remove(int index) {
        tasks.remove(index);
    }

    /**
     * Lists all the tasks in the list.
     *
     * @param ui Ui object that shows result to user.
     */
    public void list(Ui ui) {
        ui.addToResponseMessage("Here are the tasks in your list:");
        int numOfTasks = tasks.size();
        for (int i = 1; i <= numOfTasks; i++) {
            ui.addToResponseMessage(i + "." + tasks.get(i - 1));
        }
    }

    /**
     * Marks a task at the 1-based task number as done.
     *
     * @param taskNumber 1-based task number.
     * @param storage Storage in the disk.
     */
    public void mark(int taskNumber, Storage storage) {
        assert taskNumber >= 0 : "taskNumber is non-negative";
        Task task = tasks.get(taskNumber - 1);
        task.markAsDone();
        try {
            storage.writeTasksToFile(this);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Marks a task at the 1-based task number as not done.
     *
     * @param taskNumber 1-based task number.
     * @param storage Storage in the disk.
     */
    public void unmark(int taskNumber, Storage storage) {
        assert taskNumber >= 0 : "taskNumber is non-negative";
        Task task = tasks.get(taskNumber - 1);
        task.markAsNotDone();
        try {
            storage.writeTasksToFile(this);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Deletes a task at the 1-based task number.
     *
     * @param taskNumber 1-based task number.
     * @param storage Storage in the disk.
     */
    public Task deleteTask(int taskNumber, Storage storage) {
        assert taskNumber >= 0 : "taskNumber is non-negative";
        Task task = tasks.remove(taskNumber - 1);
        try {
            storage.writeTasksToFile(this);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return task;
    }

    /**
     * Adds a new task to the list.
     *
     * @param newTask Task to be added.
     * @param storage Storage in the disk.
     */
    public void addTask(Task newTask, Storage storage) {
        tasks.add(newTask);
        try {
            storage.addTaskToFile(newTask);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Searches the task description that matches the string.
     *
     * @param str Search string.
     * @param ui Ui object that shows response to user.
     */
    public void searchTask(String str, Ui ui) {
        assert str != "" : "str is supposed to be non-empty";
        ArrayList<Task> results = new ArrayList<>();
        ArrayList<Integer> indices = new ArrayList<>();
        int index = 1;
        for (Task task : tasks) {
            if (task.getDescription().contains(str)) {
                results.add(task);
                indices.add(index);
            }
            index++;
        }
        ui.addToResponseMessage("Here are the matching tasks in your list:");
        for (int i = 0; i < results.size(); i++) {
            ui.addToResponseMessage(indices.get(i) + ". " + results.get(i).toString());
        }
    }
}
