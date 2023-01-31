package Bob;

import org.apache.commons.lang3.StringUtils;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
        Boolean validMatches = StringUtils.countMatches(s, " /from ") == 1
                && StringUtils.countMatches(s, " /to ") == 1;

        // A valid command would have 3 different sections with this split
        String[] splitCommand = s.split(" /from | /to ");

        // Check if a description exists
        String[] command_desc = splitCommand[0].split(" ");


        return validMatches
                && splitCommand.length == 3
                && command_desc.length > 1
                && command_desc[0].equals("event")
                && s.indexOf("/from") < s.indexOf("/to") // A valid command has /from before /to
                && isDate(splitCommand[1])
                && isDate(splitCommand[2]);
    }

    // Command: deadline <description> /by <deadline>
    private static boolean isDeadline(String s) {
        String[] splitCommand = s.split(" /by ");
        String[] command_desc = splitCommand[0].split(" ", 2);

        return splitCommand.length == 2
                && command_desc.length == 2
                && command_desc[0].equals("deadline")
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

    // Return the index from a mark/unmark/delete command
    public static int parseIndex(String s) throws BobException {
        if (!isMark(s) && !isUnmark(s) && !isDelete(s)) {
            throw new BobException("Invalid mark/unmark command!");
        }
        String[] commands = s.split(" ");
        int index = Integer.parseInt(commands[1]);

        return index;
    }

    public static Todo parseTodo(String s) throws BobException {
        if (!isTodo(s)) {
            throw new BobException("Invalid todo command!");
        }

        String[] command = s.split(" ", 2);
        Todo t = new Todo(command[1]);

        return t;
    }

    public static Event parseEvent(String s) throws BobException {
        if (!isEvent(s)) {
            throw new BobException("Invalid event command!");
        }

        String[] command = s.split( " /from | /to ");
        String[] command_desc = command[0].split(" ", 2);
        String description = command_desc[1];
        LocalDate start = LocalDate.parse(command[1], format);
        LocalDate end = LocalDate.parse(command[2], format);

        Event e = new Event(description, start, end);
        return e;
    }

    public static Deadline parseDeadline(String s) throws BobException {
        if (!isDeadline(s)) {
            throw new BobException("Invalid deadline command!");
        }

        String[] splitCommand = s.split(" /by ");
        String[] command_desc = splitCommand[0].split(" ", 2);
        String description = command_desc[1];
        LocalDate deadline = LocalDate.parse(splitCommand[1], format);
        
        Deadline d = new Deadline(description, deadline);
        return d;
    }
}
