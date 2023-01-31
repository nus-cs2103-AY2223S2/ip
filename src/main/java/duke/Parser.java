package duke;

import duke.command.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {

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

    public static LocalDate stringToDate(String duration) {
        LocalDate localDate = LocalDate.parse(duration);
        return localDate;
    }

    public static String dateToString(LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }
}
