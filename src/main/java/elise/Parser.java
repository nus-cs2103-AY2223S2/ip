package elise;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Parser class to parse user inputs.
 */
public class Parser {
    // Word enum for commands
    private enum Word {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND, HELP
    }

    /**
     * Parses user input into command.
     *
     * @param sc Scanner for system input.
     * @return Command parsed.
     */
    protected static Command read(Scanner sc) throws EliseException {
        int rank;
        Word command;
        try {
            command = Word.valueOf(sc.next().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new EliseException("Invalid input.");
        }
        String s = sc.nextLine().trim();
        String[] message;

        if (s.length() > 500) {
            throw new EliseException("Message body is too long!");
        }
        switch (command) {
        case BYE:
            return new Command(0);
        case LIST:
            return new Command(1);
        case MARK:
            try {
                rank = Integer.parseInt(s);
                return new Command(2, rank - 1);
            } catch (NumberFormatException e) {
                throw new EliseException("OOPS! mark must have an integer rank");
            }
        case UNMARK:
            try {
                rank = Integer.parseInt(s);
                return new Command(3, rank - 1);
            } catch (NumberFormatException e) {
                throw new EliseException("OOPS! unmark must have an integer rank");
            }
        case TODO:
            if (s.isEmpty()) {
                throw new EliseException("OOPS!!! The description of a todo cannot be empty.");
            }
            return new Command(4, new String[] {s});
        case DEADLINE:
            message = s.split("/by ");
            message = Arrays.stream(message).map(String::trim).toArray(String[]::new);
            if (message.length != 2 || message[0].isEmpty() || message[1].isEmpty()) {
                throw new EliseException("OOPS!!! Command should be in the format 'deadline [D] /by [D]'\n"
                        + "The descriptions, [] cannot be empty.");
            }
            return new Command(5, message);
        case EVENT:
            int indexFrom = s.indexOf("/from");
            int indexTo = s.indexOf("/to");
            message = s.split("/from |/to ");
            message = Arrays.stream(message).map(String::trim).toArray(String[]::new);
            if (message.length != 3 || message[0].isEmpty()
                    || message[1].isEmpty() || message[2].isEmpty()
                    || indexFrom == -1 || indexTo == -1 || indexFrom >= indexTo) {
                throw new EliseException("OOPS!!! Command should be in the format 'event [M] /from [D] /to [D]'\n"
                        + "The descriptions, [] cannot be empty.");
            }
            return new Command(6, message);
        case DELETE:
            try {
                rank = Integer.parseInt(s);
                return new Command(7, rank - 1);
            } catch (NumberFormatException e) {
                throw new EliseException("OOPS! delete must have an integer rank.");
            }
        case FIND:
            if (s.isEmpty()) {
                throw new EliseException("Specify a keyword.");
            }
            return new Command(8, s);
        case HELP:
            return new Command(9);
        default:
            // Unreachable
            return null;
        }

    }

    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

    /**
     * Parses string to produce a MaybeDate.
     *
     * @param s Time period.
     * @return MaybeDate of the period.
     */
    protected static MaybeDate parseDate(String s) {
        String temp = s;
        if (s.length() == 10) {
            temp = temp + " 2359";
        }
        try {
            return new MaybeDate(LocalDateTime.parse(temp, INPUT_FORMAT));
        } catch (DateTimeParseException e) {
            return new MaybeDate(s);
        }
    }
}
