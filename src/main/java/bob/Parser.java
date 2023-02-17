package bob;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.apache.commons.lang3.StringUtils;

/**
 * Utility class to parse commands related to Bob
 */
public class Parser {
    private static final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd[ ha]");

    // Check if string can be parsed to LocalDate
    private static boolean isDate(String s) {
        try {
            LocalDate.parse(s, format);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    //Check that the string is a number
    private static boolean isInt(String s) {
        if (s == null) {
            return false;
        }
        // Check that every char is a digit
        for (int i = 0, len = s.length(); i < len; i++) {
            char c = s.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    // Command: todo <description>
    private static boolean isTodo(String s) {
        String[] command = s.split(" ");
        return command.length > 1 && command[0].equals("todo");
    }

    // Command: event <description> /from <start> /to <4pm>
    private static boolean isEvent(String s) {
        // A valid event would have only 1 /to and /from command
        boolean validMatches = StringUtils.countMatches(s, " /from ") == 1
                && StringUtils.countMatches(s, " /to ") == 1;

        // A valid command would have 3 different sections with this split
        String[] splitCommand = s.split(" /from | /to ");

        // Check if a description exists
        String[] commandAndDescription = splitCommand[0].split(" ");


        return validMatches
                && splitCommand.length == 3
                && commandAndDescription.length > 1
                && commandAndDescription[0].equals("event")
                && s.indexOf("/from") < s.indexOf("/to") // A valid command has /from before /to
                && isDate(splitCommand[1])
                && isDate(splitCommand[2]);
    }

    // Command: deadline <description> /by <deadline>
    private static boolean isDeadline(String s) {
        String[] splitCommand = s.split(" /by ");
        String[] commandAndDescription = splitCommand[0].split(" ", 2);

        return splitCommand.length == 2
                && commandAndDescription.length == 2
                && commandAndDescription[0].equals("deadline")
                && isDate(splitCommand[1]);
    }

    // Determine if a string can be used to mark a task
    private static boolean isMark(String s) {
        String[] words = s.split(" ");

        return words.length == 2
                && words[0].equals("mark")
                && isInt(words[1]);
    }

    // Determine if a string can be used to unmark a task
    private static boolean isUnmark(String s) {
        String[] words = s.split(" ");

        return words.length == 2
                && words[0].equals("unmark")
                && isInt(words[1]);
    }

    private static boolean isDelete(String input) {
        String[] command = input.split(" ");
        return command.length == 2 && command[0].equals("delete") && isInt(command[1]);
    }

    private static boolean isFind(String input) {
        String[] command = input.split(" ", 2);
        return command.length == 2 && command[0].equals("find");
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
