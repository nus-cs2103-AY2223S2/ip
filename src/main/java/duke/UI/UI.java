package duke.UI;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import duke.task.Task;
import duke.task.TaskList;

/**
 * Return appropriate replies or updates user on certain tasks
 * depending on user input.
 */
public class UI {
    protected static final String OUTPUT_DATE_FORMAT = "MMM dd yyyy HH:mm";
    protected static final String INDENTATION = "    ";
    private static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private Scanner scanner;
    private StringBuilder response;

    /**
     * The constructor of UI.
     */
    public UI() {
        scanner = new Scanner(System.in);
        response = new StringBuilder();
    }

    /**
     * Shows the response of Duke to the user.
     * @return The response of Duke to the user.
     */
    public String show() {
        return response.toString() + "\n";
    }

    /**
     * Reads the command entered by the user/
     * @return The command entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays the welcome message.
     */
    public void welcomeResponse() {
        System.out.println("Good day! My name is Duke. \n" + logo + "\nHow am I of service today?");
    }

    /**
     * Prints the response of Duke.
     * @param lines Responses to be shown to the user.
     */
    public void printResponse(String ... lines) {
        int linesLength = lines.length;

        for (int i = 0; i < linesLength; i++) {
            String indentedResponse = INDENTATION + lines[i];
            response = response.append(indentedResponse + "\n");
            System.out.println(indentedResponse);
        }
    }

    /**
     * Clears off all previous responses.
     */
    public void clearResponse() {
        response = new StringBuilder();
    }

    /**
     * Displays the goodbye message.
     */
    public void goodbyeResponse() {
        printResponse("It has been a great pleasure serving you. \n" + "Have a nice day.");
    }

    /**
     * Returns the date and time in output format.
     * @param dateTime Date and Time.
     * @return The String from of date and time.
     */
    public static String getOutputDateFormat(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern(OUTPUT_DATE_FORMAT));
    }

    /**
     * Shows successful marking done of the task to the user.
     * @param task The task that is marked as done.
     */
    public void showMark(Task task) {
        String mark = "Nice work! This task has been marked as done:";
        printResponse(mark, "  " + task.toString());
    }

    /**
     * Shows successful unmarking of a previously done task to the user.
     * @param task The task that is unmarked as done.
     */
    public void showUnmark(Task task) {
        String unmark = "Noted. This task has been marked as not done yet:";
        printResponse(unmark, "  " + task.toString());
    }

    /**
     * Shows an error message to the user.
     * @param error The error in the command.
     */
    public void showError(Exception error) {
        printResponse(error.getMessage());
        System.err.println(INDENTATION + error);
    }

    /**
     * Prints an empty line.
     */
    public void printEmptyLine() {
        response.append("\n");
        System.out.println();
    }

    /**
     * Shows successful deletion of a task to the user.
     * @param task The task deleted.
     * @param taskList The current lit of tasks.
     */
    public void showDelete(Task task, TaskList taskList) {
        printResponse("Understood. I have removed this task:");
        printResponse("\n" + task);
        printResponse("Now you have " + taskList.numberOfTasks() + " tasks in your list.");
    }
}
