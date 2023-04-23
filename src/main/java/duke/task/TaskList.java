package duke.task;

import java.io.IOException;
import java.util.ArrayList;

import duke.storage.Storage;
import duke.UI.UI;

/**
 * The list of task of the user.
 */
public class TaskList {
    private static ArrayList<Task> tasks;

    /**
     * The constructor of TaskList.
     * @param tasks Tasks to be tracked.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Retrieves the task from the 0th index of the list.
     * @param taskNumber The index of the task.
     * @return The task at the corresponding number.
     */
    public Task getTask(int taskNumber) {
        return tasks.get(taskNumber);
    }

    /**
     * Adds a new task to the list.
     * @param task Task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Counts the number of tasks in the list.
     * @return The current number of tasks in the list.
     */
    public int numberOfTasks() {
        return tasks.size();
    }

    /**
     * Removes the task from the list.
     * @param taskNumber The index of the task to be removed.
     */
    public void remove(int taskNumber) {
        tasks.remove(taskNumber);
    }

    /**
     * Lists all the existing tasks in the list.
     * @param ui The UI object that displays results to the user.
     */
    public void list(UI ui) {
        ui.printResponse("Here are the tasks in your list:");
        int numberOfTasks = tasks.size();
        for (int i = 1; i <= numberOfTasks; i++) {
            ui.printResponse(i + "." + tasks.get(i - 1));
        }
    }

    /**
     * Marks a task as done.
     * @param taskNumber The index of the task to be marked as done.
     * @param storage Storage in the disk
     */
    public void mark(int taskNumber, Storage storage) {
        assert taskNumber >= 1 : "taskNumber is positive.";
        Task nameOfTask = tasks.get(taskNumber - 1);
        nameOfTask.markAsDone();

        try {
            storage.save(this);
        } catch (IOException error) {
            System.err.println(error.getMessage());
        }
    }

    /**
     * Marks a previously marked as done task as not done.
     * @param taskNumber The index of the previously marked done task to be marked as undone.
     * @param storage Storage in the disk.
     */
    public void unmark(int taskNumber, Storage storage) {
        assert taskNumber >= 1 : "taskNumber is positive.";
        Task nameOfTask = tasks.get(taskNumber - 1);
        nameOfTask.unmarkAsDone();
        try {
            storage.save(this);
        } catch (IOException error) {
            System.err.println(error.getMessage());
        }
    }

    /**
     * Adds a new task to the list.
     * @param taskToAdd New task to be added.
     * @param storage Storage in the disk.
     */
    public void addTask(Task taskToAdd, Storage storage) {
        tasks.add(taskToAdd);
        try {
            storage.addToStorage(taskToAdd);
        } catch (IOException error) {
            System.err.println(error.getMessage());
        }
    }

    /**
     * Deletes an existing task.
     * @param taskNumber The index of the task to be deleted.
     * @param storage Storage in the disk.
     * @return The task to be deleted.
     */
    public Task deleteTask(int taskNumber, Storage storage) {
        assert taskNumber >= 1 : "taskNumber is positive";
        Task taskToBeDeleted = tasks.remove(taskNumber - 1);
        try {
            storage.save(this);
        } catch (IOException error) {
            System.err.println(error.getMessage());
        }
        return taskToBeDeleted;
    }

    /**
     * Finds the task description that contains the string.
     * @param string The keyword to be matched onto the existing task lists.
     * @param ui UI object that shows response to the user.
     */
    public void findTask(String string, UI ui) {
        assert string != "" : "string must be non-empty.";
        ArrayList<Task> findResults = new ArrayList<>();
        ArrayList<Integer> taskNumber = new ArrayList<>();
        int num = 1;

        for (Task task : tasks) {
            if (task.getNameOfTask().contains(string)) {
                findResults.add(task);
                taskNumber.add(num);
            }
            num++;
        }
        ui.printResponse("I have found these matching tasks in your list:");

        for (int i = 0; i < findResults.size(); i++) {
            ui.printResponse(taskNumber.get(i) +". " + findResults.get(i).toString());
        }
    }
}
