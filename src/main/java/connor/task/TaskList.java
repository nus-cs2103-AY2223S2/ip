package connor.task;

import java.util.Collections;
import java.util.LinkedList;

import connor.ui.Ui;

/**
 * TaskList object to keep track of current commands relating to tasks.
 */
public class TaskList {

    /** Collections of tasks for this instance. */
    private LinkedList<Task> tasks;

    /** Constructor to instantiate a new TaskList object. */
    public TaskList() {
        this.tasks = new LinkedList<>();
    }

    /** Constructor to instantiate a TaskList object using existing date from memory. */
    public TaskList(LinkedList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task into the collection of tasks.
     *
     * @param task the task to be added into the collection of tasks.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes a specific task as specified by the user number input.
     *
     * @param number the task to be deleted (list starts at 1).
     * @param ui prints the message.
     * @throws InvalidTaskException if number is invalid or is not a number.
     */
    public String deleteTask(String number, Ui ui) throws InvalidTaskException {
        try {
            int value = Integer.parseInt(number);
            if (number.length() < 1 || value > this.tasks.size() || value <= 0) {
                throw new InvalidTaskException();
            } else {
                Task task = this.tasks.remove(value - 1);
                return ui.deleteTaskMessage(task, this.tasks.size());
            }
        } catch (NumberFormatException e) {
            throw new InvalidTaskException();
        }
    }

    /**
     * Deletes all task in the Collection.
     */
    public void deleteAllTask() {
        this.tasks.clear();
    }

    /**
     * Marks the specific task as done and print a confirmation message.
     *
     * @param number the task to be marked done (list starts at 1).
     * @param ui prints the message.
     * @return String that indicates a task is marked done.
     * @throws InvalidTaskException if the specified number is invalid.
     */
    public String markDone(int number, Ui ui) throws InvalidTaskException {
        if (number > this.tasks.size()) {
            throw new InvalidTaskException();
        }
        assert(number - 1 < this.tasks.size());
        this.tasks.get(number - 1).mark();
        return ui.markDoneMessage(this.tasks.get(number - 1).toString());
    }

    /**
     * Marks the specific task as undone and print a confirmation message.
     *
     * @param number the task to be marked undone (list starts at 1).
     * @param ui prints the message.
     * @return String that indicates a task is marked undone.
     * @throws InvalidTaskException if the specified number is invalid.
     */
    public String markUndone(int number, Ui ui) throws InvalidTaskException {
        if (number > this.tasks.size()) {
            throw new InvalidTaskException();
        }
        assert(number - 1 < this.tasks.size());
        this.tasks.get(number - 1).unmark();
        return ui.markUndoneMessage(this.tasks.get(number - 1).toString());
    }

    /**
     * Returns a String representing the tasks in the list.
     * Meant to be printed to the user.
     *
     * @return String representation of the tasks in the list.
     */
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            str.append(i + 1)
                    .append(".")
                    .append(this.tasks.get(i).toString())
                    .append("\n");
        }

        str.append("I have ")
                .append(this.tasks.size())
                .append(" tasks in my memory")
                .append("\n");
        return str.toString();
    }

    /**
     * Returns a String representation of all tasks with the keyword.
     *
     * @param keyword the keyword to be searched
     * @return String representation of all tasks with the keyword.
     */
    public String find(String keyword) {
        StringBuilder str = new StringBuilder();
        str.append("Here are the matching results:\n");
        int counter = 1;
        for (Task task : this.tasks) {
            if (task.getTaskName().contains(keyword)) {
                str.append("  ")
                        .append(counter++)
                        .append(".")
                        .append(task)
                        .append("\n");
            }
        }
        return str.toString();
    }

    /**
     * Sorts the list according to the priority of each task.
     *
     * @param ui the ui to facilitate printing success message.
     * @return String that indicates a successful sorting.
     */
    public String sort(Ui ui) {
        Collections.sort(this.tasks, Task::compareTo);
        return ui.sortMessage();
    }

    public int getSize() {
        return this.tasks.size();
    }

    public LinkedList<Task> getList() {
        return this.tasks;
    }
}
