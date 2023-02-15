package elise;

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
    protected static Command read(String sCommand) throws EliseException {
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
            return new Command(0);
        case LIST:
            return new Command(1);
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
            return new Command(9);
        default:
            // Unreachable
            return null;
        }
    }

    private static Command find(String s) throws EliseException {
        if (s.isEmpty()) {
            throw new EliseException("Specify a keyword.");
        }
        return new Command(8, s);
    }

    private static Command delete(String s) throws EliseException {
        int rank;
        try {
            rank = Integer.parseInt(s);
            return new Command(7, rank - 1);
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
        if (message.length != 3 || message[0].isEmpty()
                || message[1].isEmpty() || message[2].isEmpty()
                || indexFrom == -1 || indexTo == -1 || indexFrom >= indexTo) {
            throw new EliseException("OOPS!!! Command should be in the format 'event [M] /from [D] /to [D]'\n"
                    + "The descriptions, [] cannot be empty.");
        }
        return new Command(6, message);
    }

    private static Command deadline(String s) throws EliseException {
        String[] message;
        message = s.split("/by ");
        message = Arrays.stream(message).map(String::trim).toArray(String[]::new);
        if (message.length != 2 || message[0].isEmpty() || message[1].isEmpty()) {
            throw new EliseException("OOPS!!! Command should be in the format 'deadline [D] /by [D]'\n"
                    + "The descriptions, [] cannot be empty.");
        }
        return new Command(5, message);
    }

    private static Command todo(String s) throws EliseException {
        if (s.isEmpty()) {
            throw new EliseException("OOPS!!! The description of a todo cannot be empty.");
        }
        return new Command(4, new String[]{s});
    }

    private static Command unmark(String s) throws EliseException {
        int rank;
        try {
            rank = Integer.parseInt(s);
            return new Command(3, rank - 1);
        } catch (NumberFormatException e) {
            throw new EliseException("OOPS! unmark must have an integer rank");
        }
    }

    private static Command mark(String s) throws EliseException {
        int rank;
        try {
            rank = Integer.parseInt(s);
            return new Command(2, rank - 1);
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
    protected static MaybeDate parseDate(String s) {
        return DateParser.parseDate(s);
    }
}
