package fea.tasklist;

import java.time.LocalDateTime;
import java.util.ArrayList;

import fea.Main;
import fea.exceptions.FeaException;
import fea.storage.Storage;
import fea.task.Task;
import fea.ui.Ui;

/**
 * TaskList class that handles all tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    /**
     * Adds a task to the task list, saves the new task list to the data file
     * and then prints the task that was added.
     *
     * @param task The task to be added.
     * @param storage The storage object that handles saving to the data file.
     * @param ui The ui object that handles printing to the user.
     * @return String The string of the task to add.
     * @throws FeaException If there is an exception saving to the data file.
     */
    public String addTask(Task task, Storage storage, Ui ui) throws FeaException {
        this.tasks.add(task);
        storage.saveTasks(this.tasks);
        return ui.printTask(task, this.tasks, true);
    }
    /**
     * Deletes a task in the task list, saves the new task list to the data file
     * and then prints the task that was just removed.
     *
     * @param taskNum The task number of the task to be removed in task list.
     * @param storage The storage object that handles saving to the data file.
     * @param ui The ui object that handles printing to the user.
     * @return String The string of the deleted task.
     * @throws FeaException If there is an exception saving to the data file.
     */
    public String deleteTask(int taskNum, Storage storage, Ui ui) throws FeaException {
        int taskListIndex = taskNum - 1;
        Task taskToRemove = this.tasks.get(taskListIndex);
        this.tasks.remove(taskListIndex);
        storage.saveTasks(this.tasks);
        return ui.printTask(taskToRemove, this.tasks, false);
    }
    /**
     * Marks a task in the task list as done or undone, saves the new task list to the data file
     * and then prints the task that was just marked or unmarked.
     *
     * @param taskNum The task number of the task to be marked or unmarked in task list.
     * @param storage The storage object that handles saving to the data file.
     * @param ui The ui object that handles printing to the user.
     * @param toMark True if the task is to be marked as done, false otherwise.
     * @return String The string of the marked or unmarked task.
     * @throws FeaException If there is an exception saving to the data file.
     */
    public String markTask(int taskNum, Storage storage, Ui ui, boolean toMark) throws FeaException {
        int taskListIndex = taskNum - 1;
        if (!this.tasks.get(taskListIndex).getMark().equals(toMark ? 'X' : ' ')) {
            this.tasks.get(taskListIndex).toggleMark();
            storage.saveTasks(this.tasks);
        }
        return ui.printMarkTask(this.tasks.get(taskListIndex), toMark);
    }
    /**
     * Gets a task in the task list given the task index.
     *
     * @param index The index of the task in the task list.
     * @return Task The task at the given index.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return int The number of tasks in the task list.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Returns the tasks which contains the keyword.
     *
     * @return String The string of all tasks that contains the keyword.
     */
    public String findTask(String keyword, Ui ui) {
        ArrayList<Integer> matchingTasksIndex = new ArrayList<>();
        for (int i = 0; i < this.tasks.size(); i++) {
            if (this.tasks.get(i).getDescription().contains(keyword)) {
                matchingTasksIndex.add(i);
            }
        }
        return ui.printMatchingTasks(matchingTasksIndex, this.tasks);
    }

    /**
     * Sets a reminder for a task.
     * @param taskNum The task number of the task to set a reminder in task list.
     * @param storage The storage object that handles saving to the data file.
     * @param ui The ui object that handles printing to the user.
     * @param reminder
     * @return String The string of the task with the reminder.
     * @throws FeaException If there is an exception saving to the data file.
     */
    public String setReminder(int taskNum, Storage storage, Ui ui, LocalDateTime reminder) throws FeaException {
        int taskListIndex = taskNum - 1;
        this.tasks.get(taskListIndex).setReminder(reminder);
        Main.scheduleReminder(this.tasks.get(taskListIndex));
        storage.saveTasks(this.tasks);
        return ui.printReminderTask(this.tasks.get(taskNum - 1));
    }
}
