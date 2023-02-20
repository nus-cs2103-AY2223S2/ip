package duke;

import java.util.ArrayList;

import duke.command.Command;
import duke.task.Task;

/**
 * Represents a tasklist.
 */
public class TaskList {
    private ArrayList<Task> array;
    private Command lastCommand = null;

    /**
     * Returns an empty task list.
     */
    public TaskList() {
        array = new ArrayList<>();
    }

    /**
     * Adds a task to the end of the list.
     * @param task task to be added.
     */
    public void addTask(Task task) {
        array.add(task);
    }

    /**
     * Deletes a task from an index.
     * @param index index of task to delete.
     */
    public void deleteTask(int index) {
        array.remove(index);
    }

    /**
     * Marks a task at an index.
     * @param index index of task to mark.
     * @param mark final status of task.
     */
    public void markTask(int index, boolean mark) {
        assert index < array.size() : "index cannot be out of bounds";
        this.getTask(index).setStatus(mark);
    }

    /**
     * Returns task at index.
     * @param index index of task to return.
     * @return task at index.
     */
    public Task getTask(int index) {
        if (index < array.size()) {
            return array.get(index);
        } else {
            return null;
        }
    }

    /**
     * Returns length of task list.
     * @return length of task list.
     */
    public int getLength() {
        return array.size();
    }

    /**
     * Returns the last command.
     * @return last command.
     */
    public Command getLastCommand() {
        return this.lastCommand;
    }

    /**
     * Stores the last command.
     */
    public void setLastCommand(Command command) {
        this.lastCommand = command;
    }
}
