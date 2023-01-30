package duke.ui;

import java.util.Scanner;

import duke.data.TaskList;
import duke.data.task.Task;

/**
 * Ui of application.
 */
public class Ui {

    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String DIVIDER = "----------------------------------------------------";

    private Scanner sc;

    /**
     * Constructor for Ui.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Reads the next command from input.
     *
     * @return String of command.
     */
    public String readCommand() {
        String cmd = sc.nextLine();
        return cmd;
    }

    /**
     * Display to user task count.
     *
     * @param size Number of tasks.
     */
    public void showTaskCount(int size) {
        System.out.println("There are now " + Integer.toString(size) + " task(s) in the list.");
    }

    /**
     * Displays to user the task added.
     *
     * @param task Task to be added.
     */
    public void showAddTask(Task task) {
        System.out.println("Understood. I have added the task:\n" + task.toString());
    }

    /**
     * Displays to user the task deleted.
     *
     * @param task Task to be deleted.
     */
    public void showDeleteTask(Task task) {
        System.out.println("Noted. I have removed the task:\n" + task.toString());
    }

    /**
     * Displays to user that tasks have been saved.
     */
    public void showSavedTasks() {
        System.out.println("Tasks have been saved.");
    }

    /**
     * Displays all tasks in TaskList.
     *
     * @param tasks TaskList containing tasks to be displayed.
     */
    public void showAllTasks(TaskList tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks added yet.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i).toString());
            }
        }
    }

    /**
     * Displays task marked.
     *
     * @param task Task to be marked.
     */
    public void showMarked(Task task) {
        System.out.println(task.outputMarked() + task.toString());
    }

    /**
     * Displays task unmarked.
     *
     * @param task Task to be unmarked.
     */
    public void showUnmarked(Task task) {
        System.out.println(task.outputUnmarked() + task.toString());
    }

    /**
     * Displays welcome message.
     */
    public void showWelcome() {
        showLine();
        System.out.println(LOGO + "Hello! I'm Duke. How may I be of assistance?\n");
        showLine();
    }

    /**
     * Displays goodbye message.
     */
    public void showGoodbye() {
        showLine();
        System.out.println("Thank you for your patronage. Goodbye!\n");
        showLine();
    }

    /**
     * Displays error message.
     *
     * @param error Error to be displayed.
     */
    public void showError(String error) {
        System.out.println("Error: " + error);
    }

    /**
     * Displays divider line.
     */
    public void showLine() {
        System.out.println(DIVIDER);
    }

}
