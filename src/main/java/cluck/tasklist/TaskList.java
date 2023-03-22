package cluck.tasklist;

import java.util.ArrayList;

import cluck.exceptions.TaskIndexOutOfBoundsException;
import cluck.tasks.Task;

/**
 * TaskList contains the tasks.
 */
public class TaskList {
    private final ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Marks the task at a given index.
     *
     * @param taskIndex Index of task in ArrayList.
     * @return Task at index given if mark operation successful.
     * @throws TaskIndexOutOfBoundsException If index given is out of bounds.
     */
    public Task markTask(int taskIndex) throws TaskIndexOutOfBoundsException {
        try {
            Task currTask = taskList.get(taskIndex);
            currTask.mark();
            return currTask;
        } catch (IndexOutOfBoundsException e) {
            throw new TaskIndexOutOfBoundsException();
        }
    }

    /**
     * Un-marks the task at a given index.
     *
     * @param taskIndex Index of task in ArrayList.
     * @return Task at index given if un-mark operation successful.
     * @throws TaskIndexOutOfBoundsException If index given is out of bounds.
     */
    public Task unmarkTask(int taskIndex) throws TaskIndexOutOfBoundsException {
        try {
            Task currTask = taskList.get(taskIndex);
            currTask.unmark();
            return currTask;
        } catch (IndexOutOfBoundsException e) {
            throw new TaskIndexOutOfBoundsException();
        }
    }

    /**
     * Adds a task to the task list.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Deletes a task from task list at the given index.
     *
     * @param taskIndex Index of task to be deleted.
     * @return Return task if deletion successful.
     * @throws TaskIndexOutOfBoundsException If index out of bounds of ArrayList.
     */
    public Task deleteTask(int taskIndex) throws TaskIndexOutOfBoundsException {
        try {
            return taskList.remove(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskIndexOutOfBoundsException();
        }
    }

    /**
     * Converts all the tasks in task list to a single String in save format.
     *
     * @return String containing all the tasks in the task list.
     */
    public String toSaveFormat() {
        StringBuilder toWrite = new StringBuilder();
        for (Task task : taskList) {
            toWrite.append(task.makeSaveFormat());
        }
        return toWrite.toString();
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return Number of tasks in the list.
     */
    public int taskCount() {
        return taskList.size();
    }

    /**
     * Find tasks with description containing the given key word.
     * This method is not case-sensitive.
     *
     * @param keyWord The key word.
     * @return Task list containing only the tasks with matching description.
     */
    public TaskList findMatches(String keyWord) {
        TaskList matchingTasks = new TaskList();
        for (Task task : taskList) {
            if (task.containsKeyWord(keyWord)) {
                matchingTasks.addTask(task);
            }
        }
        return matchingTasks;
    }

    /**
     * The toString method of the task list returns a String of all the tasks in order of when they were added.
     *
     * @return String displaying all the tasks.
     */
    public String toString() {
        StringBuilder allTasks = new StringBuilder();
        int counter = 1;
        for (Task task : taskList) {
            allTasks.append(counter);
            allTasks.append(") ");
            allTasks.append(task.toString());
            allTasks.append("\n");
            counter += 1;
        }
        return allTasks.toString();
    }


}
