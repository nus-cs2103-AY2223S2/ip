package seedu.duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Ui {
    protected final static String OUTPUT_DATE_PATTERN = "MMM dd yyyy HHmm";
    protected final static String INPUT_DATE_PATTERN = "yyyy-mm-dd HHmm";
    protected final static String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";

    private static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static void printLogo() {
        System.out.println("Hello from\n" + logo);
    }

    public static void indentedPrintln(String ... messages) {
        int len = messages.length
        for (int i = 0; i < len; i++) {
            String indentedMessage = "     " + messages[i];
            System.out.println(indentedMessage);
        }
    }

    public static LocalDateTime parseDateTime(String s) {
        return LocalDateTime.parse(s, DateTimeFormatter.ofPattern(INPUT_DATE_PATTERN));
    }

    public static String getDateTimeOutput(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern(OUTPUT_DATE_PATTERN));
    }
}
