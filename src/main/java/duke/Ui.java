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
        String logo = "\n"
                + "                 _        \n"
                + "                | |       \n"
                + "   ___ _ __ __ _| |_ ___  \n"
                + "  / _ \\ '__/ _` | __/ _ \\ \n"
                + " |  __/ | | (_| | || (_) |\n"
                + "  \\___|_|  \\__,_|\\__\\___/ \n"
                + "                          \n";

        return "Hello from\n" + logo + "\n"
                + showSepLine()
                + "Hello~! I'm Erato, your secretary! What can I do for you?\n"
                + showSepLine()
                + this.showHelp();
    }

    /**
     * Prints additional information on how to use each command.
     *
     * @return String of Help message
     */
    public String showHelp() {
        return "You require help? Here are some commands you can use!\n"
                + "\n"
                + "Commands:\n"
                + "Adding tasks: todo, event, deadline\n"
                + "Marking completion: mark, unmark\n"
                + "Removing tasks: remove\n"
                + "List all tasks: list\n"
                + "Find tasks with keyword: find\n"
                + "Exit Duke: exit\n\n"
                + "help <command> for more info.";
    }

    /**
     * Prints a goodbye message.
     *
     * @return String of Goodbye message
     */
    public String showGoodbye() {
        return "Bye! Hope to see you again soon! I'll keep your tasks here ready for you!";
    }

    /**
     * Prints a separation line.
     *
     * @return String of a separation line.
     */
    public static String showSepLine() {
        return "========================================\n";
    }

    /**
     * Prints an error message.
     *
     * @param message Error message
     * @return String of error message
     */
    public String showError(String message) {
        return "Oh no! I can't process that!\n" + message;
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
        return "Ok~ I have added: " + task + "\n"
                + "Now you have " + tasks.size() + " task(s) in the list.\n"
                + "Would you like to add any more?";
    }

    /**
     * Prints details of when a Task is deleted to the list.
     *
     * @param task Task that is deleted.
     * @param tasks Current TaskList.
     * @return String of details when a Task is deleted.
     */
    public String showDeleteTask(Task task, TaskList tasks) {
        return "Ok~ I have deleted: " + task + "\n"
                + "Now you have " + tasks.size() + " task(s) in the list.";
    }

    /**
     * Prints details of when a Task in the list is marked.
     *
     * @param task Task that is marked.
     * @return String of details when a Task is marked.
     */
    public String showMarkTask(Task task) {
        return "Alright! I've marked this task as done: " + task;
    }

    /**
     * Prints details of when a Task in the list is unmarked.
     *
     * @param task Task that is unmarked.
     * @return String of details when a Task is unmarked.
     */
    public String showUnmarkTask(Task task) {
        return "You've changed your mind? I've unmarked this task as done: " + task;
    }

    public String showFoundTasks(TaskList keywordTasks) {
        return "I've found the matching tasks in your list!\n"
                + keywordTasks;
    }
}
