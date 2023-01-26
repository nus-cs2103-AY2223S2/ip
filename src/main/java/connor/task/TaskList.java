package connor.task;

import connor.ui.Ui;

import java.util.LinkedList;

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
    public void deleteTask(String number, Ui ui) throws InvalidTaskException {
        try {
            int value = Integer.parseInt(number);
            if (number.length() < 1 || value > this.tasks.size() || value <= 0) {
                throw new InvalidTaskException();
            } else {
                Task task = this.tasks.remove(value - 1);
                ui.deleteTaskMessage(task, this.tasks.size());
            }
        } catch (NumberFormatException e){
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
     */
    public void markDone(int number, Ui ui) {
        this.tasks.get(number - 1).mark();
        ui.markDoneMessage(this.tasks.get(number - 1).toString());
    }

    /**
     * Marks the specific task as undone and print a confirmation message.
     *
     * @param number the task to be marked undone (list starts at 1).
     * @param ui prints the message.
     */
    public void markUndone(int number, Ui ui) {
        this.tasks.get(number - 1).unmark();
        ui.markUndoneMessage(this.tasks.get(number - 1).toString());
    }

    /**
     * Returns a String representing the tasks in the list.
     * Meant to be printed to the user.
     *
     * @return String representation of the tasks in the list.
     */
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(Ui.LINE).append("\n");
        for (int i = 0; i < this.tasks.size(); i++) {
            str.append("        ")
                    .append(i + 1)
                    .append(".")
                    .append(this.tasks.get(i).toString())
                    .append("\n");
        }

        str.append("        I have ")
                .append(this.tasks.size())
                .append(" tasks in my memory")
                .append("\n");
        return str.toString();
    }

    public String find(String input) {
        StringBuilder str = new StringBuilder();
        str.append(Ui.LINE).append("        HERE ARE THE MATCHING RESULTS:\n");
        int counter = 1;
        for (int i = 0; i < this.tasks.size(); i++) {
            if (this.tasks.get(i).getTaskName().contains(input)) {
                str.append("        ")
                        .append(counter++)
                        .append(".")
                        .append(this.tasks.get(i).toString())
                        .append("\n");
            }
        }
        return str.toString();
    }

    public int getSize() {
        return this.tasks.size();
    }

    public LinkedList<Task> getList() {
        return this.tasks;
    }
}
