package duke;

import duke.command.*;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {

    public static DateTimeFormatter strFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    public static DateTimeFormatter ldtFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");


    public static Integer stringToInt(String index) {
        return Integer.parseInt(index);
    }

    public static Command stringToCommand(String command) {
        if (command.startsWith("delete")) {
            return new DeleteCommand(command);
        } else if (command.startsWith("mark")) {
            return new MarkCommand(command);
        } else if (command.startsWith("unmark")) {
            return new UnmarkCommand(command);
        } else if (command.equals("list")) {
            return new ListCommand(command);
        } else if (command.startsWith("todo") || command.startsWith("deadline") || command.startsWith("event")) {
            return new AddCommand(command);
        } else if (command.equals("bye")) {
            return new ExitCommand("bye");
        } else  {
            return new ErrorCommand("error");
        }
    }

    public static String[] deadlineSplit(String deadline) {
        return deadline.split(" ", 2);
    }

    public static String[] eventSplit(String event) {
        return event.split(" ", 6);
    }

    public static LocalDateTime stringToDateTime(String duration) {
        LocalDateTime localDateTime = LocalDateTime.parse(duration, strFormatter);
        return localDateTime;
    }

    public static String dateTimeToString(LocalDateTime localDateTime) {
        return localDateTime.format(ldtFormatter);
    }

    public static String dateTimeSaving(LocalDateTime localDateTime) {
        return localDateTime.format(strFormatter);
    }
}
