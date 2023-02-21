package cluck.taskList;

import java.util.ArrayList;
import java.lang.StringBuilder;

import cluck.messages.Messages;
import cluck.exceptions.TaskIndexOutOfBoundsException;
import cluck.tasks.Task;

/**
 * TaskList contains the tasks. In a list. Yea.
 */
public class TaskList {
    private final ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Marks the task at a given index.
     *
     * @param taskIndex index of task in ArrayList
     * @return task at index given if mark operation successful
     * @throws TaskIndexOutOfBoundsException if index given is out of bounds
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
     * @param taskIndex index of task in ArrayList.
     * @return task at index given if un-mark operation successful.
     * @throws TaskIndexOutOfBoundsException if index given is out of bounds
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
     * @param task task to be added.
     * @return task added
     */
//Todo: have commands print the task added
    public Task addTask(Task task) {
        this.taskList.add(task);
        return task;
    }

    /**
     * Deletes a task from task list at the given index.
     *
     * @param taskIndex index of task to be deleted.
     * @return return task if deletion successful.
     * @throws TaskIndexOutOfBoundsException if index out of bounds of ArrayList
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
     * @return the string
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
     * @return number of tasks in the list
     */
    public int taskCount() {
        return taskList.size();
    }

    /**
     * Find tasks with description containing the given key word.
     * This method is not case-sensitive.
     *
     * @param keyWord the key word
     * @return task list containing only the tasks with matching description
     */
    public TaskList findMatches(String keyWord) {
        TaskList matchingTasks = new TaskList();
        int counter = 0;
        for (Task task : taskList) {
            if (task.containsKeyWord(keyWord)) {
                counter += 1;
                matchingTasks.addTask(task);
            }
        }
        return matchingTasks;
    }


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
