package dude.task;

import java.util.ArrayList;
import java.util.List;

import dude.exception.DudeException;

/**
 * A list of Tasks
 */
public class TaskList {
    private List<Task> tasks;
    private List<Task> tasksCheckpoint;

    /**
     * Initializes TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
        tasksCheckpoint = null;
    }

    /**
     * Initializes TaskList.
     *
     * @param tasks List of task to store into TaskList.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
        tasksCheckpoint = null;
    }

    /**
     * Gets Task in TaskList at given task index.
     *
     * @param taskIndex Index of Task.
     * @return Task at given task index.
     */
    public Task getTask(int taskIndex) {
        assert taskIndex > 0 : "Task index should be more than 0";
        return tasks.get(taskIndex - 1);
    }

    /**
     * Adds Task into TaskList.
     *
     * @param task Task object to be added into TaskList.
     */
    public void addTask(Task task) throws DudeException {
        assert task != null : "New task should not be null";
        backupTasks();
        tasks.add(task);
        Task.addTaskCount();
    }

    /**
     * Deletes Task from TaskList.
     *
     * @param taskIndex Index of Task to be deleted from TaskList.
     */
    public void deleteTask(int taskIndex) throws DudeException {
        assert taskIndex > 0 : "Task index should be more than 0";
        backupTasks();
        tasks.remove(taskIndex - 1);
        Task.decreaseTaskCount();
    }

    /**
     * Marks Task in TaskList.
     *
     * @param taskIndex Index of Task to be mark in TaskList.
     */
    public void markTask(int taskIndex) throws DudeException {
        assert taskIndex > 0 : "Task index should be more than 0";
        backupTasks();
        tasks.get(taskIndex - 1).mark();
    }

    /**
     * Unmarks Task in TaskList.
     *
     * @param taskIndex Index of Task to be unmark in TaskList.
     */
    public void unmarkTask(int taskIndex) throws DudeException {
        assert taskIndex > 0 : "Task index should be more than 0";
        backupTasks();
        tasks.get(taskIndex - 1).unmark();
    }

    /**
     * Returns raw output of TaskList.
     *
     * @return Raw output of TaskList.
     */
    public String toRaw() {
        StringBuilder input = new StringBuilder();
        for (Task task : tasks) {
            input.append(task.toRaw());
        }
        return input.toString();
    }

    /**
     * Returns string output of TaskList.
     *
     * @return String output of TaskList.
     */
    @Override
    public String toString() {
        StringBuilder result;
        if (tasks.size() != 0) {
            result = new StringBuilder("Here are the tasks in your list: \n");
            for (int i = 0; i < tasks.size(); i++) {
                result.append(i + 1).append(".").append(tasks.get(i).toString()).append("\n");
            }
        } else {
            result = new StringBuilder("Eh... You currently got no task leh.\n");
        }
        return result.toString();
    }

    /**
     * Clones the current state of tasks into backup.
     */
    public void backupTasks() throws DudeException {
        try {
            List<Task> tempTasks = new ArrayList<>();
            for (Task task : this.tasks) {
                Task newTask = (Task) task.clone();
                tempTasks.add(newTask);
            }
            this.tasksCheckpoint = new ArrayList<>(tempTasks);
        } catch (CloneNotSupportedException e) {
            throw new DudeException("Eh... I am unable to copy from the backup tasks");
        }
    }

    /**
     * Restores the previous state of tasks into current task list.
     *
     * @return Boolean output on the success of the operation.
     */
    public boolean undo() {
        if (tasksCheckpoint == null) {
            return false;
        }
        tasks = new ArrayList<>(tasksCheckpoint);
        Task.setTaskCount(tasks.size());
        tasksCheckpoint = null;
        return true;
    }
}
