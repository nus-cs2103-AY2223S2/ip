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
    public void markTask(int index) throws DukeException {
        // handle errors out of range
        if (index < 0 || index >= this.getSize()) {
            int display = index + 1;
            throw new DukeException("duke.task.Task " + display + " does not exist.");
        }
        this.getTask(index).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this.getTask(index));
    }

    /**
     * Unmarks a task based on the task index provided
     * @param index
     * @throws DukeException
     */
    public void unmarkTask(int index) throws DukeException {
        // handle errors out of range
        if (index < 0 || index >= this.getSize()) {
            int display = index + 1;
            throw new DukeException("duke.task.Task " + display + " does not exist.");
        }
        this.getTask(index).markAsUndone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(this.getTask(index));
    }

    /**
     * Add task to taskList
     * @param task
     */
    public void addTask(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        taskList.add(task);
        System.out.println("Now you have " + this.getSize() + " tasks in the list.");
    }

    /**
     * Remove task from taskList
     * @param index
     * @throws DukeException
     */
    public void removeTask(int index) throws DukeException {
        // handle errors out of range
        if (index < 0 || index >= this.getSize()) {
            int display = index + 1;
            throw new DukeException("duke.task.Task " + display + " does not exist.");
        }
        System.out.println("Noted. I've removed this task:");
        System.out.println(this.getTask(index));
        taskList.remove(index);
        System.out.println("Now you have " + this.getSize() + " tasks in the list.");
    }

    /**
     * Finds and prints task details based on keyword by user
     * @param keyword
     * @throws DukeException
     */
    public void findTask(String keyword) throws DukeException {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < this.getSize(); i++) {
            if (this.getTask(i).getStorageDetails().contains(keyword)) {
                this.ui.sendTaskDetails(i + 1, this.getTask(i));
            }
        }
    }
}
