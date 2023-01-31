package duke.tasklist;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.task.Task;
import duke.ui.Ui;

/**
 * TaskList stores all tasks and performs operation to manage the tasks
 */
public class TaskList {
    protected ArrayList<Task> taskList;
    private Ui ui;

    /**
     * Constructor for TaskList
     * @param taskList
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
        this.ui = new Ui();
    }

    public int getSize() {
        return this.taskList.size();
    }

    /**
     * Obtain task based on given index
     * @param index
     * @return Task
     * @throws DukeException
     */
    public Task getTask(int index) throws DukeException {
        // handle errors out of range
        if (index < 0 || index >= this.getSize()) {
            int display = index + 1;
            throw new DukeException("duke.task.Task " + display + " does not exist.");
        }
        return taskList.get(index);
    }

    /**
     * Marks a task based on the task index provided
     * Throws an exception if task does not exist
     * @param index
     * @throws DukeException
     */
    public String markTask(int index) throws DukeException {
        String s = "";
        // handle errors out of range
        if (index < 0 || index >= this.getSize()) {
            int display = index + 1;
            throw new DukeException("duke.task.Task " + display + " does not exist.");
        }
        this.getTask(index).markAsDone();
        s = s + "Nice! I've marked this task as done: \n";
        s = s + this.getTask(index).toString();
        return s;
    }

    /**
     * Unmarks a task based on the task index provided
     * @param index
     * @throws DukeException
     */
    public String unmarkTask(int index) throws DukeException {
        String s = "";
        // handle errors out of range
        if (index < 0 || index >= this.getSize()) {
            int display = index + 1;
            throw new DukeException("duke.task.Task " + display + " does not exist.");
        }
        this.getTask(index).markAsUndone();
        s = s + "OK, I've marked this task as not done yet:";
        s = s + this.getTask(index).toString();
        return s;
    }

    /**
     * Add task to taskList
     * @param task
     */
    public String addTask(Task task) {
        String s = "Got it. I've added this task:\n";
        s = s + task.toString() + "\n";
        taskList.add(task);
        s = s + "Now you have " + this.getSize() + " tasks in the list.";
        return s;
    }

    /**
     * Remove task from taskList
     * @param index
     * @throws DukeException
     */
    public String removeTask(int index) throws DukeException {
        String s = "";
        // handle errors out of range
        if (index < 0 || index >= this.getSize()) {
            int display = index + 1;
            throw new DukeException("duke.task.Task " + display + " does not exist.");
        }
        s = s + "Noted. I've removed this task:\n";
        s = s + this.getTask(index).toString() + "\n";
        taskList.remove(index);
        s = s + "Now you have " + this.getSize() + " tasks in the list.";
        return s;
    }

    /**
     * Finds and prints task details based on keyword by user
     * @param keyword
     * @throws DukeException
     */
    public String findTask(String keyword) throws DukeException {
        String s = "Here are the matching tasks in your list:";
        for (int i = 0; i < this.getSize(); i++) {
            if (this.getTask(i).getStorageDetails().contains(keyword)) {
                s = s + this.ui.sendTaskDetails(i + 1, this.getTask(i));
            }
        }
        return s;
    }
}
