package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.Arrays;

public class Parser {

    static protected Command read(Scanner sc) {
        String command = sc.next();
        // Useful variables
        int rank;
        String s = sc.nextLine().trim();
        String[] message;
        try {
            if (s.length() > 500) {
                throw new DukeException("Message body is too long!");
            }
            switch (command) {
                case "bye":
                    return new Command(0);
                case "list":
                    return new Command(1);
                case "mark":
                    try {
                        rank = Integer.parseInt(s);
                        return new Command(2, rank - 1);
                    } catch (NumberFormatException e) {
                        throw new DukeException("OOPS! mark must have an integer rank");
                    }

                case "unmark":
                    try {
                        rank = Integer.parseInt(s);
                        return new Command(3, rank - 1);
                    } catch (NumberFormatException e) {
                        throw new DukeException("OOPS! unmark must have an integer rank");
                    }
                case "todo":
                    if (s.isEmpty()) {
                        throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                    }
                    return new Command(4, new String[] {s});
                case "deadline":
                    message = s.split("/by ");
                    message = Arrays.stream(message).map(String::trim).toArray(String[]::new);
                    if (message.length != 2 || message[0].isEmpty() || message[1].isEmpty()) {
                        throw new DukeException("OOPS!!! duke.Command should be in the format 'deadline [M] /by [M]'\n" +
                                "The description, [M] cannot be empty.");
                    }
                    return new Command(5, message);
                case "event":
                    int indexFrom = s.indexOf("/from");
                    int indexTo = s.indexOf("/to");
                    message = s.split("/from |/to ");
                    message = Arrays.stream(message).map(String::trim).toArray(String[]::new);
                    if (message.length != 3 || message[0].isEmpty() ||
                            message[1].isEmpty() || message[2].isEmpty() ||
                            indexFrom == -1 || indexTo == -1 || indexFrom >= indexTo) {
                        throw new DukeException("OOPS!!! duke.Command should be in the format 'event [M] /from [M] /to [M]'\n" +
                                "The description, [M] cannot be empty.");
                    }
                    return new Command(6, message);
                case "delete":
                    try {
                        rank = Integer.parseInt(s);
                        return new Command(7, rank - 1);
                    } catch (NumberFormatException e) {
                        throw new DukeException("OOPS! delete must have an integer rank");
                    }

                default:
                    throw new DukeException("Invalid input.");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            return new Command(-1);
        }
    }

    private static final DateTimeFormatter INPUTFORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

    protected static MaybeDate parseDate(String s) {
        String temp = s;
        if (s.length() == 10) {
            temp = temp + " 2359";
        }
        try {
            return new MaybeDate(LocalDateTime.parse(temp, INPUTFORMAT));
        } catch (DateTimeParseException e) {
            return new MaybeDate(s);
        }
    }

    public static void main(String[] args) {
        try {
            parseDate("01-12-2019");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }
}
