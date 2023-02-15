package elise.internal;

import elise.EliseException;
import elise.MaybeDate;
import elise.commands.*;

import java.util.Arrays;

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
     * @param sCommand Scanner for system input.
     * @return Command parsed.
     */
    public static Command read(String sCommand) throws EliseException {
        Word command;
        String[] val = sCommand.split(" ", 2);
        try {
            command = Word.valueOf(val[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new EliseException("Invalid input.");
        }
        String s = val.length > 1 ? val[1].trim() : "";

        if (s.length() > 500) {
            throw new EliseException("Message body is too long!");
        }
        switch (command) {
        case BYE:
            return new NoArgumentCommand(-1);
        case LIST:
            return new NoArgumentCommand(1);
        case MARK:
            return mark(s);
        case UNMARK:
            return unmark(s);
        case TODO:
            return todo(s);
        case DEADLINE:
            return deadline(s);
        case EVENT:
            return event(s);
        case DELETE:
            return delete(s);
        case FIND:
            return find(s);
        case HELP:
            return new NoArgumentCommand(0);
        default:
            // Unreachable
            return null;
        }
    }

    private static Command find(String s) throws EliseException {
        if (s.isEmpty()) {
            throw new EliseException("Specify a keyword.");
        }
        return new FindCommand(0, s);
    }

    private static Command delete(String s) throws EliseException {
        int rank;
        try {
            rank = Integer.parseInt(s);
            return new IndexCommand(2, rank - 1);
        } catch (NumberFormatException e) {
            throw new EliseException("OOPS! delete must have an integer rank.");
        }
    }

    private static Command event(String s) throws EliseException {
        String[] message;
        int indexFrom = s.indexOf("/from");
        int indexTo = s.indexOf("/to");
        message = s.split("/from |/to ");
        message = Arrays.stream(message).map(String::trim).toArray(String[]::new);
        boolean wrongMessage = message.length != 3 || message[0].isEmpty()
                || message[1].isEmpty() || message[2].isEmpty();
        boolean wrongFormat = indexFrom == -1 || indexTo == -1 || indexFrom >= indexTo;
        if (wrongMessage || wrongFormat) {
            throw new EliseException("OOPS!!! Command should be in the format 'event [M] /from [D] /to [D]'\n"
                    + "The descriptions, [] cannot be empty.");
        }
        return new CreateCommand(2, message);
    }

    private static Command deadline(String s) throws EliseException {
        String[] message;
        message = s.split("/by ");
        message = Arrays.stream(message).map(String::trim).toArray(String[]::new);
        if (message.length != 2 || message[0].isEmpty() || message[1].isEmpty()) {
            throw new EliseException("OOPS!!! Command should be in the format 'deadline [D] /by [D]'\n"
                    + "The descriptions, [] cannot be empty.");
        }
        return new CreateCommand(1, message);
    }

    private static Command todo(String s) throws EliseException {
        if (s.isEmpty()) {
            throw new EliseException("OOPS!!! The description of a todo cannot be empty.");
        }
        return new CreateCommand(0, new String[]{s});
    }

    private static Command unmark(String s) throws EliseException {
        int rank;
        try {
            rank = Integer.parseInt(s);
            return new IndexCommand(1, rank - 1);
        } catch (NumberFormatException e) {
            throw new EliseException("OOPS! unmark must have an integer rank");
        }
    }

    private static Command mark(String s) throws EliseException {
        int rank;
        try {
            rank = Integer.parseInt(s);
            return new IndexCommand(0, rank - 1);
        } catch (NumberFormatException e) {
            throw new EliseException("OOPS! mark must have an integer rank");
        }
    }

    /**
     * Parses string to produce a MaybeDate.
     *
     * @param s Time period.
     * @return MaybeDate of the period.
     */
    public static MaybeDate parseDate(String s) {
        return DateParser.parseDate(s);
    }
}
