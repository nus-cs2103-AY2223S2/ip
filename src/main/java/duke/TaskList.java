package duke;

import java.util.ArrayList;
import duke.task.Task;

/** Manages the task list of Duke. */
public class TaskList {

    private ArrayList<Task> list;
    private Ui ui;

    /**
     * Initializes a TaskList.
     * 
     * @param ui Ui object for the current session.
     */
    public TaskList(Ui ui) {
        this.list = new ArrayList<Task>();
        this.ui = ui;
    }

    /**
     * Marks the task at the given index as done.
     * 
     * @param index Index of task to be marked as done.
     */
    public void markTaskAsDone(int index) {
        Task task = this.list.get(index);
        task.markAsDone();
        this.ui.addToMessage("Nice! I've marked this task as done:");
        this.ui.addToMessage(task.toString());
    }

    /**
     * Marks the task at the given index as undone.
     * 
     * @param index Index of task to be marked as undone.
     */
    public void markTaskAsUndone(int index) {
        Task task = this.list.get(index);
        task.unmarkAsDone();
        this.ui.addToMessage("Ok, I've marked this task as not done yet:");
        this.ui.addToMessage(task.toString());
    }

    /**
     * Adds a task to the task list.
     * 
     * @param task Task to be added to the task list
     */
    public void addTask(Task task) {
        this.list.add(task);
        this.ui.addToMessage("Got it. I've added this task:");
        this.ui.addToMessage(task.toString());
        this.ui.addToMessage(String.format("Now you have %d tasks in the list.", list.size()));
    }

    /**
     * Deletes a task from the task list.
     * 
     * @param index Task to be deleted from the task list.
     */
    public void deleteTask(int index) {
        Task currentTask = list.get(index);
        list.remove(index);
        this.ui.addToMessage("Noted. I've removed this task:");
        this.ui.addToMessage(currentTask.toString());
    }

    /**
     * Find all tasks that contain the given keyword.
     * 
     * @param keyword The keyword to find tasks with
     */
    public void findTasks(String keyword) {
        this.ui.addToMessage("Here are the matching tasks in your list:");
        int numMatchingTasks = 1;
        for (Task task : this.list) {
            if (task.hasKeyword(keyword)) {
                this.ui.addToMessage(String.format("%s: %s", numMatchingTasks, task.toString()));
                numMatchingTasks += 1;
            }
        }
        if (numMatchingTasks == 1) {
            this.ui.clearMessage();
            this.ui.addToMessage(String.format("Uh oh! You don't have any tasks matching '%s'", keyword));
        }
    }

    /** Displays the current list of tasks. */
    public void displayTasks() {
        if (list.size() == 0) {
            this.ui.addToMessage("You have no tasks! Try adding some.");
        } else {
            this.ui.addToMessage("Here are the tasks in your list:");
            for (int i = 0; i < this.list.size(); i++) {
                Task currentTask = this.list.get(i);
                String s = String.format("%d: %s", i + 1, currentTask.toString());
                this.ui.addToMessage(s);
            }
        }
    }

    /**
     * Serializes all the tasks in the task list.
     * 
     * @return A string containing the serialized tasks. Each line is the string
     *         representation of a task in the task list
     */
    public String serializeTasks() {
        StringBuilder sb = new StringBuilder();
        for (Task task : this.list) {
            sb.append(task.serialize());
            sb.append("\n");
        }
        return sb.toString();
    }

    public void clearTasks() {
        this.list.clear();
    }
}
