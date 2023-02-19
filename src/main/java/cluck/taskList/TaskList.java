package cluck.taskList;

import java.util.ArrayList;
import java.lang.StringBuilder;

import cluck.exceptions.TaskIndexOutOfBoundsException;
import cluck.tasks.Task;

/**
 * TaskList contains the tasks. In a list. Yea.
 */
public class TaskList {
    private final ArrayList<Task> taskList = new ArrayList<>();

    public TaskList() {
    }

    /**
     * @param taskIndex index of task in ArrayList.
     * @return task at index given if mark operation was succssful.
     * @throws TaskIndexOutOfBoundsException is thrown when the index given is out of bounds
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
     * @param taskIndex index of task in ArrayList.
     * @return task at index given if un-mark operation was successful.
     * @throws TaskIndexOutOfBoundsException if given index lies outside taskList's range
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
     * @param task task to be added.
     * @return task added
     */
    public Task addTask(Task task) {
        this.taskList.add(task);
        return task;
    }

    public String toSave() {
        StringBuilder savedTasks = new StringBuilder();
        for (Task t : taskList) {
            savedTasks.append(t.makeSaveFormat());
        }
        return savedTasks.toString();
    }

    /**
     * @param taskIndex index of task to be deleted.
     * @return return task if deletion was successful.
     * @throws TaskIndexOutOfBoundsException if index out of bounds of ArrayList
     */
    public Task deleteTask(int taskIndex) throws TaskIndexOutOfBoundsException {
        try {
            return taskList.remove(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskIndexOutOfBoundsException();
        }
    }




}
