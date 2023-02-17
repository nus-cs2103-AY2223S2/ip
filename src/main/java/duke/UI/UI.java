package duke.UI;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import duke.task.Task;
import duke.task.TaskList;

/**
 * The skeleton version of Duke.
 * Return appropriate replies or updates user on certain tasks
 * depending on user input.
 */
public class UI {
    protected static final String OUTPUT_DATE_FORMAT = "MMM dd yyyy HHmm";
    protected static final String INDENTATION = "    ";
    private static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private Scanner scanner;
    private StringBuilder response;

    public UI() {
        scanner = new Scanner(System.in);
        response = new StringBuilder();
    }

    public String show() {
        return response.toString();
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void welcomeResponse() {
        System.out.println("Good day! My name is Duke. \n" + logo + "\nHow am I of service today?");
    }

    public void printResponse(String ... lines) {
        int linesLength = lines.length;

        for (int i = 0; i < linesLength; i++) {
            String indentedResponse = INDENTATION + lines[i];
            response.append(indentedResponse + "\n");
            System.out.println(indentedResponse);
        }
    }

    public void clearResponse() {
        response = new StringBuilder();
    }

    public void printError(Exception error) {
        printResponse(error.getMessage());
        System.err.println(INDENTATION + error);
    }

    public void goodbyeResponse() {
        printResponse("It has been a great pleasure serving you. \n" + "Have a nice day.");
    }

    public static String getOutputDateFormat(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern(OUTPUT_DATE_FORMAT));
    }

    public void showMark(Task task) {
        printResponse("Nice work! This task has been marked as done:");
        printResponse("  " + task);
    }

    public void showUnmark(Task task) {
        printResponse("Noted. This task has been marked as not done yet:");
        printResponse("  " + task);
    }

    public void showError(Exception error) {
        printResponse(error.getMessage());
        System.err.println(error);
    }

    public void printEmptyLine() {
        System.out.println("\n");
    }

    public void showDelete(Task task, TaskList taskList) {
        printResponse("Understood. I have removed this task:");
        printResponse("\n" + task);
        printResponse("Now you have " + taskList.numberOfTasks() + " tasks in your list.");
    }
}
