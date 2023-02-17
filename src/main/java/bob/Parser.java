package bob;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

/**
 * Utility class to parse commands related to Bob
 */
public class Parser {
    private static final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // Check if string can be parsed to LocalDate
    private static boolean isDate(String s) {
        try {
            LocalDate.parse(s, format);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /** Determine if a string is a valid todo command */
    private static boolean isTodo(String s) {
        // Command: todo <description>
        boolean isValidTodo = Pattern.matches("^todo [\\w ]+$", s);
        return isValidTodo;
    }

    /** Determine if a string is a valid event command */
    private static boolean isEvent(String s) {
        // Command: event <description> /from <start> /to <4pm>
        String regex = "^event [\\w ]+ /from \\d{4}-\\d{2}-\\d{2} /to \\d{4}-\\d{2}-\\d{2}$";
        boolean hasValidPattern = Pattern.matches(regex, s);

        // ['from', <startDate>, <endDate>]
        String[] splitCommand = s.split(" /from | /to ");
        String startDate = splitCommand[1];
        String endDate = splitCommand[2];
        boolean hasValidDates = isDate(startDate) && isDate(endDate);

        return hasValidPattern && hasValidDates;
    }

    /** Determine if a string is a valid deadline command */
    private static boolean isDeadline(String s) {
        // Command: deadline <description> /by <deadline>
        String regex = "^deadline [\\w ]+ /by \\d{4}-\\d{2}-\\d{2}$";
        boolean hasValidPattern = Pattern.matches(regex, s);

        // ['deadline', <date>]
        String[] splitCommand = s.split(" /by ");
        String date = splitCommand[1];
        boolean hasValidDate = isDate(date);

        return hasValidPattern && hasValidDate;
    }

    /** Determine if a string is a valid mark command */
    private static boolean isMark(String s) {
        boolean isValidMark = Pattern.matches("^mark \\d+$", s);

        return isValidMark;
    }

    /** Determine if a string is a valid unmark command */
    private static boolean isUnmark(String s) {
        boolean isValidUnmark = Pattern.matches("^unmark \\d+$", s);

        return isValidUnmark;
    }

    /** Determine if a string is a valid delete command */
    private static boolean isDelete(String s) {
        boolean isValidDelete = Pattern.matches("^delete \\d+$", s);

        return isValidDelete;
    }

    /** Determine if a string is a valid find command */
    private static boolean isFind(String s) {
        boolean isValidFind = Pattern.matches("^find [\\w ]+$", s);
        return isValidFind;
    }

    /**
     * Parses and returns index specified in a mark/unmark/delete command
     * @param s String command
     * @return Integer representing index specified
     * @throws BobException
     */
    public static int parseIndex(String s) throws BobException {
        if (!isMark(s) && !isUnmark(s) && !isDelete(s)) {
            throw new BobException("Invalid mark/unmark command!");
        }
        String[] commands = s.split(" ");
        int index = Integer.parseInt(commands[1]);

        return index;
    }

    /**
     * Validates and parses a given string,
     * and returns a Todo object if the string is valid.
     * @param s String to be validated and parsed
     * @return Todo object
     * @throws BobException If string is not a valid todo command
     */
    public static Todo parseTodo(String s) throws BobException {
        if (!isTodo(s)) {
            throw new BobException("Invalid todo command!");
        }

        String[] command = s.split(" ", 2);
        Todo t = new Todo(command[1]);

        return t;
    }

    /**
     * Validates and parses a given string,
     * and returns an Event object if the string is valid.
     * @param s String to be validated and parsed
     * @return Event object
     * @throws BobException If string is not a valid Event command
     */
    public static Event parseEvent(String s) throws BobException {
        if (!isEvent(s)) {
            throw new BobException("Invalid event command!");
        }

        String[] command = s.split(" /from | /to ");
        String[] commandAndDescription = command[0].split(" ", 2);
        String description = commandAndDescription[1];
        LocalDate start = LocalDate.parse(command[1], format);
        LocalDate end = LocalDate.parse(command[2], format);

        Event e = new Event(description, start, end);
        return e;
    }

    /**
     * Validates and parses a given string,
     * and returns a Deadline object if the string is valid.
     * @param s String to be validated and parsed
     * @return Deadline object
     * @throws BobException If string is not a valid Deadline command
     */
    public static Deadline parseDeadline(String s) throws BobException {
        if (!isDeadline(s)) {
            throw new BobException("Invalid deadline command!");
        }

        String[] splitCommand = s.split(" /by ");
        String[] commandAndDescription = splitCommand[0].split(" ", 2);
        String description = commandAndDescription[1];
        LocalDate deadline = LocalDate.parse(splitCommand[1], format);
        Deadline d = new Deadline(description, deadline);
        return d;
    }

    /**
     * Validates and parses a given string,
     * returns a keyword string if input is a valid find command
     * @param s String to be validated and parsed
     * @return Keyword string
     * @throws BobException
     */
    public static String parseFind(String s) throws BobException {
        if (!isFind(s)) {
            throw new BobException("Invalid find command!");
        }

        String[] command = s.split(" ", 2);
        return command[1];
    }
}
