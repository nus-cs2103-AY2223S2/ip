package duke.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import duke.task.TaskList;
import duke.task.Task;

/**
 *
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

    public Ui() {
        sc = new Scanner(System.in);
        response = new StringBuilder();
    }

    public String showResponse() {
        return response.toString();
    }

    public String readCommand() {
        return sc.nextLine();
    }

    /**
     *
     */
    public void showWelcome() {
        System.out.println("Hello from\n" + logo);
    }

    /**
     *
     * @param messages
     */
    public void addToResponseMessage(String ... messages) {
        int len = messages.length;
        for (int i = 0; i < len; i++) {
            String indentedMessage = INDENTATION + messages[i];
            response.append(indentedMessage + "\n");
            System.out.println(indentedMessage);
        }
    }

    public void clearResponse() {
        response = new StringBuilder();
    }

    /**
     *
     * @param e
     */
    public void showError(Exception e) {
        addToResponseMessage(e.getMessage());
        System.err.println(INDENTATION + e);
    }

    public void printNewLine() {
        response.append("\n");
        System.out.println();
    }

    public void showGoodbyeMessage() {
        addToResponseMessage(Ui.GOODBYE_MESSAGE);
    }

    public static String getDateTimeOutput(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern(OUTPUT_DATE_PATTERN));
    }

    public void showMarkingDone(Task task) {
        String message = "Nice! I've marked this task as done:";
        addToResponseMessage(message);
        addToResponseMessage("  " + task);
    }

    public void showUnmarkingDone(Task task) {
        String message = "OK, I've marked this task as not done yet:";
        addToResponseMessage(message);
        addToResponseMessage("  " + task);
    }

    public void showDeleteDone(Task task, TaskList tasks) {
        addToResponseMessage("Noted. I've removed this task:");
        addToResponseMessage("  " + task);
        addToResponseMessage("Now you have " + tasks.size() + " tasks in the list.");
    }
}
