package duke.tasklist;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import duke.exceptions.DukeException;
import duke.tasktypes.Task;
import duke.ui.Ui;


/**
 * Represents a collection of tasks.
 * A TaskList object corresponds to User's task collection represented by an ArrayList.
 */
public class TaskList {
    private ArrayList<Task> taskStorage;
    private int numTasks;
    private Ui ui;

    /**
     * Constructs a TaskList collection.
     * TaskList object is constructed with an Ui instance.
     * Ui object assists TaskList in communicating with User.
     *
     * @param ui Ui instance.
     */
    public TaskList(Ui ui) {
        taskStorage = new ArrayList<>();
        this.numTasks = 0;
        this.ui = ui;
    }

    /**
     * Adds task to collection.
     * Does not produce output to User.
     *
     * @param task Task instance to be added.
     */
    public void loadTask(Task task) {
        this.taskStorage.add(task);
        int originalNumTasks = this.numTasks;
        numTasks++;
        assert (originalNumTasks + 1) == this.numTasks : "Number of tasks mismatch!";
    }

    /**
     * Returns task has been added message.
     * Adds task to collection.
     * Informs User upon successful task addition.
     *
     * @param task Task instance to be added.
     * @return Task has been added message.
     */
    public String addTask(Task task) {
        this.taskStorage.add(task);
        int originalNumTasks = this.numTasks;
        numTasks++;
        assert (originalNumTasks + 1) == this.numTasks : "Number of tasks mismatch!";
        return ui.taskAdd(task, numTasks);
    }

    /**
     * Returns task has been deleted message.
     * Deletes task from collection.
     * Informs User upon successful task deletion.
     *
     * @param toDelete Task ID of task to be deleted.
     * @return Task has been deleted message.
     */
    public String deleteTask(int toDelete) throws DukeException {
        Task deleteTask = null;
        for (Task task : taskStorage) {
            if (task.getTaskID() == toDelete) {
                deleteTask = task;
                break;
            }
        }
        if (deleteTask == null) {
            throw new DukeException("Task does not exist! Please enter valid Task ID!");
        } else {
            taskStorage.remove(deleteTask);
            int originalNumTasks = this.numTasks;
            numTasks--;
            assert (originalNumTasks - 1) == this.numTasks : "Number of tasks mismatch!";
            return ui.taskDelete(deleteTask, numTasks);
        }
    }

    /**
     * Returns task has been marked done message.
     * Marks specified task in collection as complete.
     * Informs User upon successful operation.
     *
     * @param mark Task ID of task to be mark complete.
     * @return Task has been marked done message.
     */
    public String markTask(int mark) throws DukeException {
        Task markTask = null;
        for (Task task : taskStorage) {
            if (task.getTaskID() == mark) {
                markTask = task;
                break;
            }
        }
        if (markTask == null) {
            throw new DukeException("Task does not exist! Please enter valid Task ID!");
        } else {
            markTask.setDone();
            return ui.markTaskDone(markTask);
        }
    }

    /**
     * Returns task has been unmarked message.
     * Unmarks specified task in collection as incomplete.
     * Informs User upon successful operation.
     *
     * @param unmark Task ID of task to be mark incomplete.
     * @return Task has been unmarked message.
     */
    public String unmarkTask(int unmark) throws DukeException {
        Task unmarkTask = null;
        for (Task task : taskStorage) {
            if (task.getTaskID() == unmark) {
                unmarkTask = task;
                break;
            }
        }
        if (unmarkTask == null) {
            throw new DukeException("Task does not exist! Please enter valid Task ID!");
        } else {
            unmarkTask.setUndone();
            return ui.markTaskUndone(unmarkTask);
        }
    }

    /**
     * Returns String representation of tasks in Task collection.
     * Prints all current tasks in collection to standard output.
     *
     * @return String representation of tasks in Task collection.
     */
    public String printTasks() {
        if (this.numTasks == 0) {
            return "There are no available tasks at the moment!\n";
        }
        int count = 1;
        String output = "";
        output += "Here are the tasks in your list:\n";
        for (Task task : taskStorage) {
            output += String.format("%d.%s", count++, task.toString()) + "\n";
        }
        assert count == this.numTasks : "Number of tasks printed mismatch.";
        return output;
    }

    /**
     * Returns String representation of tasks in Task collection.
     * Prints all current tasks in collection ordered by deadline.
     *
     * @return String representation of tasks in Task collection.
     */
    public String printTasksInOrder() {
        if (this.numTasks == 0) {
            return "There are no available tasks at the moment!\n";
        }
        int count = 1;
        String output = "";
        output += "Here are the tasks in your list:\n";
        //@@author XylusChen-reused
        //Reused from https://stackoverflow.com/questions/5927109/sort-objects-in-arraylist-by-date
        //with minor modifications.
        List<Task> tasksSorted = taskStorage.stream()
                .sorted(Comparator.comparing(Task :: getDeadline)).collect(Collectors.toList());
        //@@author
        for (Task task : tasksSorted) {
            output += String.format("%d.%s", count++, task.toString()) + "\n";
        }
        assert count == this.numTasks : "Number of tasks printed mismatch.";
        return output;
    }

    /**
     * Returns a new TaskList object containing tasks that matched given keyword.
     *
     * @param keywords String array keywords to containing words to be matched with task descriptions.
     * @return Returns a new TaskList object containing matching tasks.
     */
    public TaskList getMatchingTasks(String[] keywords) {
        TaskList matchTasks = new TaskList(this.ui);
        for (Task task : this.taskStorage) {
            if (task.matchKeywords(keywords)) {
                matchTasks.loadTask(task);
            }
        }
        assert matchTasks.numTasks <= this.numTasks
                : "Number of matching tasks cannot exceed number of existing tasks";
        return matchTasks;
    }

    /**
     * Returns ArrayList representation of task collection.
     *
     * @return ArrayList containing all tasks.
     */
    public ArrayList<Task> getTasks() {
        return taskStorage;
    }

    /**
     * Returns the number of tasks in collection.
     *
     * @return Number of tasks in collection.
     */
    public int getNumTasks() {
        return numTasks;
    }

}
