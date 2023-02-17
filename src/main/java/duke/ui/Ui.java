package duke.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import duke.task.Task;
import duke.task.TaskList;

/**
 * Encapsulates the Ui handling logic of Duke.
 */
public class Ui {
    protected static final String OUTPUT_DATE_PATTERN = "MMM dd yyyy HH:mm";
    protected static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String INDENTATION = "     ";
    private static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private Scanner sc;
    private StringBuilder response;

    /**
     * Constructs a Ui object.
     */
    public Ui() {
        sc = new Scanner(System.in);
        response = new StringBuilder();
    }

    /**
     * Shows the response to the user.
     *
     * @return Response to the user.
     */
    public String showResponse() {
        return response.toString();
    }

    /**
     * Reads in command entered by the user in CLI terminal.
     *
     * @return Command entered by the user.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Shows welcome message.
     */
    public void showWelcome() {
        System.out.println("Hello from\n" + logo);
    }

    /**
     * Adds messages to the String temporarily storing all responses
     * to be shown.
     *
     * @param messages Messages to be shown to the user.
     */
    public void addToResponseMessage(String ... messages) {
        int len = messages.length;
        for (int i = 0; i < len; i++) {
            String indentedMessage = INDENTATION + messages[i];
            response.append(indentedMessage + "\n");
            System.out.println(indentedMessage);
        }
    }

    /**
     * Clears previous response stored within ui object.
     */
    public void clearResponse() {
        response = new StringBuilder();
    }

    /**
     * Shows error message to the user.
     *
     * @param e Error from invalid input.
     */
    public void showError(Exception e) {
        addToResponseMessage(e.getMessage());
        System.err.println(INDENTATION + e);
    }

    /**
     * Shows a new line.
     */
    public void printNewLine() {
        response.append("\n");
        System.out.println();
    }

    /**
     * Shows goodbye message to user.
     */
    public void showGoodbyeMessage() {
        addToResponseMessage(Ui.GOODBYE_MESSAGE);
    }

    /**
     * Returns the date and time in output format.
     *
     * @param dateTime Date and Time.
     * @return The String representation of the date and time.
     */
    public static String getDateTimeOutput(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern(OUTPUT_DATE_PATTERN));
    }

    /**
     * Shows successful marking done of the task to user.
     *
     * @param task The task that is marked done.
     */
    public void showMarkingDone(Task task) {
        String message = "Nice! I've marked this task as done:";
        addToResponseMessage(message);
        addToResponseMessage("  " + task);
    }

    /**
     * Shows successful unmarking of the task to user.
     *
     * @param task The task that is unmarked.
     */
    public void showUnmarkingDone(Task task) {
        String message = "OK, I've marked this task as not done yet:";
        addToResponseMessage(message);
        addToResponseMessage("  " + task);
    }

    /**
     * Shows successful deletion of task message to user.
     *
     * @param task The task deleted.
     * @param tasks The current task list.
     */
    public void showDeleteDone(Task task, TaskList tasks) {
        addToResponseMessage("Noted. I've removed this task:");
        addToResponseMessage("  " + task);
        addToResponseMessage("Now you have " + tasks.size() + " tasks in the list.");
    }
}
