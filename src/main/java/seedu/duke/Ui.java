package seedu.duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Ui {
    protected final static String OUTPUT_DATE_PATTERN = "MMM dd yyyy HH:mm";
    protected final static String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";
    private final static String INDENTATION = "     ";
    private static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private Scanner sc = new Scanner(System.in);

    public String readCommand() {
        return sc.nextLine();
    }

    public void showWelcome() {
        System.out.println("Hello from\n" + logo);
    }

    public void indentedPrintln(String ... messages) {
        int len = messages.length;
        for (int i = 0; i < len; i++) {
            String indentedMessage = INDENTATION + messages[i];
            System.out.println(indentedMessage);
        }
    }

    public void showError(Exception e) {
        System.err.println(INDENTATION + e);
    }

    public void printNewLine() {
        System.out.println();
    }

    public static String getDateTimeOutput(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern(OUTPUT_DATE_PATTERN));
    }
}
