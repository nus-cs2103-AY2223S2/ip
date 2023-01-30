package duke;
import java.util.Scanner;
import duke.task.Task;

public class Ui {
    private Scanner scanner;

    public Ui () {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads the command from stdin.
     *
     * @return String of a text command.
     */
    public String readCommand() {
        System.out.println("Input Command:");
        return scanner.nextLine();
    }

    /**
     * Shows all the tasks currently in the list and their status.
     *
     * @param tasks TaskList of current tasks.
     */
    public void showTasks(TaskList tasks) {
        this.showSepLine();
        System.out.println(tasks);
        this.showSepLine();
    }

    /**
     * Prints the welcome screen of the app.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        this.showSepLine();
        System.out.println("Hello! I'm Duke, what can I do for you?");
        this.showSepLine();

        this.showHelp();
    }

    /**
     * Prints additional information on how to use each command.
     */
    public void showHelp() {
        this.showSepLine();
        System.out.println("Commands:");
        System.out.println("Adding tasks: todo, event (requires date), deadline " +
                "(requires date in yyyy-mm-dd format)");
        System.out.println("To specify date, task description should be followed by" +
                " /[date]");
        System.out.println("Marking completion: mark (index), unmark (index)");
        System.out.println("Removing tasks: remove (index)");
        System.out.println("List all tasks: list");
        System.out.println("Exit Duke: exit");
        this.showSepLine();
    }

    /**
     * Prints a goodbye message.
     */
    public void showGoodbye() {
        System.out.println("Bye! Hope to see you again soon!");
    }

    /**
     * Prints a separation line.
     */
    public void showSepLine() {
        System.out.println("-----------------------------------------");
    }

    /**
     * Prints an error message.
     *
     * @param message Error message
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Prints an error message from a loading error.
     *
     * @param message Loading error message.
     */
    public void showLoadingError(String message) {
        showSepLine();
        System.out.println(message);
        showSepLine();
    }

    /**
     * Prints details of when a Task is added to the list.
     *
     * @param task Task that is added.
     * @param tasks Current TaskList
     */
    public void showAddTask(Task task, TaskList tasks) {
        System.out.println("Added: " + task);
        System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
    }

    /**
     * Prints details of when a Task is deleted to the list.
     *
     * @param task Task that is deleted.
     * @param tasks Current TaskList.
     */
    public void showDeleteTask(Task task, TaskList tasks) {
        System.out.println("Deleted: " + task);
        System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
    }

    /**
     * Prints details of when a Task in the list is marked.
     *
     * @param task Task that is marked.
     */
    public void showMarkTask(Task task) {
        System.out.println("I've marked this task as done: " + task);
    }

    /**
     * Prints details of when a Task in the list is unmarked.
     *
     * @param task Task that is unmarked.
     */
    public void showUnmarkTask(Task task) {
        System.out.println("I've unmarked this task as done: " + task);
    }
}
