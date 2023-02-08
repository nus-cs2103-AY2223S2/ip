package duke;

import duke.task.Task;

public class Ui {


    /**
     * Shows all the tasks currently in the list and their status.
     *
     * @param tasks TaskList of current tasks.
     * @return String of list of tasks
     */
    public String showTasks(TaskList tasks) {
        return tasks.toString();
    }

    /**
     * Prints the welcome screen of the app.
     */
    public String showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        return "Hello from\n" + logo + "\n"
                + this.showSepLine()
                + "Hello! I'm Duke, what can I do for you?\n"
                + this.showSepLine()
                + this.showHelp();
    }

    /**
     * Prints additional information on how to use each command.
     *
     * @return String of Help message
     */
    public String showHelp() {
        return "Commands:\n"
                + "Adding tasks: todo, event (requires date), deadline "
                + "(requires date in yyyy-mm-dd format)\n"
                + "To specify date, task description should be followed by"
                + " /[date]\n"
                + "Marking completion: mark (index), unmark (index)\n"
                + "Removing tasks: remove (index)\n"
                + "List all tasks: list\n"
                + "Exit Duke: exit";
    }

    /**
     * Prints a goodbye message.
     *
     * @return String of Goodbye message
     */
    public String showGoodbye() {
        return "Bye! Hope to see you again soon!";
    }

    /**
     * Prints a separation line.
     *
     * @return String of a separation line.
     */
    public String showSepLine() {
        return "----------------------------\n";
    }

    /**
     * Prints an error message.
     *
     * @param message Error message
     * @return String of error message
     */
    public String showError(String message) {
        return message;
    }

    /**
     * Prints an error message from a loading error.
     *
     * @param message Loading error message.
     */
    public String showLoadingError(String message) {
        return message;
    }

    /**
     * Prints details of when a Task is added to the list.
     *
     * @param task Task that is added.
     * @param tasks Current TaskList
     * @return String of details when a Task is added
     */
    public String showAddTask(Task task, TaskList tasks) {
        return "Added: " + task + "\n"
                + "Now you have " + tasks.size() + " task(s) in the list.";
    }

    /**
     * Prints details of when a Task is deleted to the list.
     *
     * @param task Task that is deleted.
     * @param tasks Current TaskList.
     * @return String of details when a Task is deleted.
     */
    public String showDeleteTask(Task task, TaskList tasks) {
        return "Deleted: " + task + "\n"
                + "Now you have " + tasks.size() + " task(s) in the list.";
    }

    /**
     * Prints details of when a Task in the list is marked.
     *
     * @param task Task that is marked.
     * @return String of details when a Task is marked.
     */
    public String showMarkTask(Task task) {
        return "I've marked this task as done: " + task;
    }

    /**
     * Prints details of when a Task in the list is unmarked.
     *
     * @param task Task that is unmarked.
     * @return String of details when a Task is unmarked.
     */
    public String showUnmarkTask(Task task) {
        return "I've unmarked this task as done: " + task;
    }

    public String showFoundTasks(TaskList keywordTasks) {
        return "Here are the matching tasks in your list:\n"
                + keywordTasks;
    }
}
