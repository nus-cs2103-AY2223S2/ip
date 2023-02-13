package task;

import java.util.LinkedList;
import java.util.List;

import command.CommandDeadline;
import command.CommandDelete;
import command.CommandEvent;
import command.CommandMark;
import command.CommandToDo;
import command.CommandUnMark;
import duke.DukeException;
import duke.Ui;

/**
 * List of all task.
 */
public class TaskList {

    private final List<Task> storedTasks;

    /**
     * Constructor for TaskList.
     *
     * @param storedTasks List of all tasks stored in file.
     */
    public TaskList(List<Task> storedTasks) {
        this.storedTasks = new LinkedList<>(storedTasks);
    }

    /**
     * Get a task in TaskList.
     *
     * @param index Index of task.
     * @return Task at specified index.
     * @throws DukeException If task is not in the list.
     */
    public Task getTaskAt(int index) throws DukeException {
        try {
            return this.storedTasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(Ui.integerOutOfBoundsMessage);
        }
    }

    /**
     * Remove a task from TaskList.
     *
     * @param index Starts from 1.
     * @return Task removed.
     * @throws DukeException If task is not in list.
     */
    public Task removeTaskAt(int index) throws DukeException {
        try {
            return this.storedTasks.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(Ui.integerOutOfBoundsMessage);
        }
    }

    /**
     * Add a task into TaskList.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        storedTasks.add(task);
    }

    /**
     * Find all task that contains the given phrase.
     *
     * @param phrase Phrase use for finding.
     * @return String of all task found.
     */
    public String findTaskWith(String phrase) {
        int taskIndex = 1;
        StringBuilder s = new StringBuilder("  ");

        for (Task currentTask : this.storedTasks) {
            if (currentTask.getName().contains(phrase)) {
                s.append(taskIndex).append(".").append(currentTask).append("\n  ");
                taskIndex += 1;
            }
        }
        return s.toString();
    }

    @Override
    public String toString() {
        return this.getAllTaskInListWithNumbering();
    }

    private String getAllTaskInListWithNumbering() {
        StringBuilder s = new StringBuilder();
        for (int i = 1; i <= storedTasks.size(); i++) {
            s.append(i).append(". ").append(storedTasks.get(i)).append("\n");
        }
        return s.toString();
    }

    /**
     * Format task list for file write.
     *
     * @return String of all task in file write format.
     */
    public String writeToFile() {
        StringBuilder s = new StringBuilder();
        for (Task task : this.storedTasks) {
            s.append(task.writeToFile());
            s.append("\n");
        }
        return s.toString();
    }

    /**
     * Mark task at given index.
     *
     * @param index Index of task to be mark.
     * @return String confirmation message of marked task.
     * @throws DukeException If task could not be mark.
     */
    public String markTaskAt(String index) throws DukeException {
        CommandMark c = new CommandMark(this, index);
        return c.execute();
    }

    /**
     * Unmark task at given index.
     *
     * @param index Index of task to be unmark.
     * @return String confirmation message of unmarked task.
     * @throws DukeException If task could not be unmark.
     */
    public String unMarkTaskAt(String index) throws DukeException {
        CommandUnMark c = new CommandUnMark(this, index);
        return c.execute();
    }

    /**
     * Remove task at given index.
     *
     * @param index Index of task to remove.
     * @return String confirmation of task removed.
     * @throws DukeException If task was not removed.
     */
    public String deleteTaskAt(String index) throws DukeException {
        CommandDelete c = new CommandDelete(this, index);
        return c.execute();
    }

    /**
     * Add an event type task into the TaskList.
     *
     * @param taskDetails Includes name, start date and end date.
     * @return String confirmation message if successful.
     * @throws DukeException If task was not added.
     */
    public String addEvent(String taskDetails) throws DukeException {
        CommandEvent c = new CommandEvent(this, taskDetails);
        return c.execute();
    }

    /**
     * Add a deadline type task into the TaskList.
     *
     * @param taskDetails Includes name and end date.
     * @return String confirmation message if successful.
     * @throws DukeException If task was not added.
     */
    public String addDeadline(String taskDetails) throws DukeException {
        CommandDeadline c = new CommandDeadline(this, taskDetails);
        return c.execute();
    }

    /**
     * Add a todo type task into the TaskList.
     *
     * @param taskDetails Includes name.
     * @return String confirmation message if successful.
     * @throws DukeException If task was not added.
     */
    public String addToDo(String taskDetails) throws DukeException {
        CommandToDo c = new CommandToDo(this, taskDetails);
        return c.execute();
    }

}