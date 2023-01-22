package duke.tasklist;

import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.ui.Ui;

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
     * @throws DukeException If there is an exception saving to the data file.
     */
    public void addTask(Task task, Storage storage, Ui ui) throws DukeException {
        this.tasks.add(task);
        storage.saveTasks(this.tasks);
        ui.printTask(task, this.tasks, true);
    }

    
    /**
     * Deletes a task in the task list, saves the new task list to the data file 
     * and then prints the task that was just removed.
     *  
     * @param taskNum The task number of the task to be removed in task list.
     * @param storage The storage object that handles saving to the data file.
     * @param ui The ui object that handles printing to the user.
     * @throws DukeException If there is an exception saving to the data file.
     */
    public void deleteTask(int taskNum, Storage storage, Ui ui) throws DukeException {
        int taskListIndex = taskNum - 1;
        Task taskToRemove = this.tasks.get(taskListIndex);
        this.tasks.remove(taskListIndex);
        storage.saveTasks(this.tasks);
        ui.printTask(taskToRemove, this.tasks, false);
    }

    
    /** 
     * Marks a task in the task list as done or undone, saves the new task list to the data file
     * and then prints the task that was just marked or unmarked.
     * 
     * @param taskNum The task number of the task to be marked or unmarked in task list.
     * @param storage The storage object that handles saving to the data file.
     * @param ui The ui object that handles printing to the user.
     * @param toMark True if the task is to be marked as done, false otherwise.
     * @throws DukeException If there is an exception saving to the data file.
     */
    public void markTask(int taskNum, Storage storage, Ui ui, boolean toMark) throws DukeException {
        int taskListIndex = taskNum - 1;
        if (!this.tasks.get(taskListIndex).getMark().equals(toMark ? 'X' : ' ')) {
            this.tasks.get(taskListIndex).toggleMark();
            storage.saveTasks(this.tasks);
        }
        ui.printMarkTask(this.tasks.get(taskListIndex), toMark);
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
     * Returns the number of tasks in the task list.
     * 
     * @return int The number of tasks in the task list.
     */
    public void findTask(String keyword, Ui ui) {
        ArrayList<Integer> matchingTasksIndex = new ArrayList<>();
        for (int i = 0; i < this.tasks.size(); i++) {
            if (this.tasks.get(i).getDescription().contains(keyword)) {
                matchingTasksIndex.add(i);
            }
        }
        ui.printMatchingTasks(matchingTasksIndex, this.tasks);
    }
}
