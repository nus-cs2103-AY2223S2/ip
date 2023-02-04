package duke;

import java.util.Scanner;

/**
 * A class to manage the user interface
 * and deal with interactions with the user.
 */
public class Ui {
    private static final String spacesPrefix = "     ";
    private static final String dashes = "    "
            + "______________________________"
            + "______________________________";

    private final Scanner in;

    /**
     * Constructs the Ui object given the scanner with input.
     */
    public Ui() {
        this.in = new Scanner(System.in);
    }

    /**
     * Gets the user command from input line.
     *
     * @return User command string.
     */
    public String getUserCommand() {
        System.out.println("Enter your desired action: ");
        String fullInputLine = in.nextLine();
        return fullInputLine;
    }

    /**
     * Displays welcome message to the user.
     */
    public void showWelcome() {
        String welcomeMsg = spacesPrefix
                + "Hello! I'm Duke\n"
                + spacesPrefix
                + "How can I help you?";
        System.out.println(welcomeMsg);
    }

    /**
     * Displays goodbye message to the user.
     */
    public void showGoodbye() {
        String exitMsg = spacesPrefix
                + "Farewell! See you soon!";
        System.out.println(exitMsg);
    }

    /**
     * Displays loading error message to the user.
     */
    public void showLoadingError() {
        String loadingErrorString = "Failed to load from file!";
        System.out.println(loadingErrorString);
    }

    /**
     * Displays the added task to the user given Task.
     *
     * @param task Task that has been added.
     */
    public void showTaskAdded(Task task) {
        System.out.println(formatMessage("New task added: "));
        System.out.println(spacesPrefix + task);
    }

    /**
     * Displays the removed task to the user given Task.
     *
     * @param task Task that has been removed.
     */
    public void showTaskRemoved(Task task) {
        System.out.println(formatMessage("Removed task: "));
        System.out.println(spacesPrefix + task);
    }

    /**
     * Displays the message containing number of tasks
     * to the user given TaskList.
     *
     * @param taskList TaskList for Duke.
     */
    public void showNumTasks(TaskList taskList) {
        System.out.println(spacesPrefix
                + " Now list has " + taskList.getSize() + " tasks.");
    }

    /**
     * Displays the contents of the task list to the
     * user given TaskList.
     *
     * @param taskList TaskList for Duke.
     */
    public void showList(TaskList taskList) {
        System.out.println(spacesPrefix
                + "Here are the tasks in your list:");
        for (int i = 0; i < taskList.getSize(); i++) {
            int number = i + 1;
            System.out.println(spacesPrefix + " "
                    + number + ". "
                    + taskList.getTaskList().get(i));
        }
    }

    /**
     * Displays the marked task to the user given Task.
     *
     * @param task Task that has been marked.
     */
    public void showTaskMarked(Task task) {
        System.out.println(formatMessage("Marked task as done:"));
        System.out.println(spacesPrefix + task);
    }

    /**
     * Displays the unmarked task to the user given Task.
     *
     * @param task Task that has been unmarked.
     */
    public void showTaskUnmarked(Task task) {
        System.out.println(formatMessage("Marked task as undone:"));
        System.out.println(spacesPrefix + task);
    }

    /**
     * Displays the error message to the user given
     * the error message.
     *
     * @param errorMessage Error message to be shown.
     */
    public void showError(String errorMessage) {
        System.out.println(formatMessage(errorMessage));
    }

    /**
     * Displays a line of dashes to the user.
     */
    public void showLine() {
        System.out.println(dashes);
    }

    /**
     * Displays the matching tasks message to the user.
     */
    public void showMatchingTasksMessage() {
        System.out.println(formatMessage("Matching tasks are shown below: "));
    }

    /**
     * Displays the found task with numbering.
     *
     * @param number Task number.
     * @param task Task found.
     */
    public void displayFoundTask(int number, Task task) {
        System.out.println(formatMessage(number + "." + task));
    }

    /**
     * Displays message with formatting to user.
     *
     * @param message Message to be displayed.
     */
    public void displayFormattedMessage(String message) {
        System.out.println(formatMessage(message));
    }

    public void closeScanner() {
        this.in.close();
    }

    /**
     * Adds spacing indentation to the message.
     *
     * @param message Message to be displayed.
     * @return Formatted message to be displayed.
     */
    public String formatMessage(String message) {
        return spacesPrefix + message;
    }
}
