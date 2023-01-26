package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {

    /**
     * Changes the format of the date and time for storage.
     * @param str String object of the displayed date and time.
     * @return String object of the date and time to be saved.
     */
    public static String parseDate(String str) {
        LocalDateTime date = LocalDateTime.parse(str, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        String displayDate = date.format(dateFormat);
        return displayDate;
    }

    /**
     * Changes the format of the date and time for display.
     * @param str String object of the saved date and time.
     * @return String object of the date and time used for display.
     */
    public static String retrieveDate(String str) {
        LocalDateTime date = LocalDateTime.parse(str, DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        String displayDate = date.format(dateFormat);
        return displayDate;
    }

    /**
     * Splits the input of the user into relevant chunks to be used.
     * @param input String input of the user.
     * @return String array consisting of the content and date of tasks.
     */
    public static String[] parseTask(String input) {
        String[] splitCommand = input.split(" ", 2);
        String type = splitCommand[0];
        if(type.equals("todo")) {
            return splitCommand;
        } else {
            String[] segments = splitCommand[1].split("/");
            return segments;
        }
    }

    public static int findIndex(String input) {
        String[] splitCommand = input.split(" ", 2);
        int index = Integer.parseInt(splitCommand[1]) - 1;
        return index;
    }
}