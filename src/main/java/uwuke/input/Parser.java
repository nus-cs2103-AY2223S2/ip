package uwuke.input;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import uwuke.output.DukeException;

/**
 * Main input processor with only static methods that attempts to extract useful information from user input.
 * Note that this class should only be used when the type of command is identified and is of the correct format. 
 * Methods implemented in this class assumes a specific regex, very limited debugging information is given 
 * by this class when a string in the wrong format is passed in to any of the methods.
 */
public abstract class Parser {
    /**
     * Processes string into useful information when command is identified as a deadline command
     * <p> Regular Expression: deadline\\s.+/by\\s.+$
     * 
     * @param input deadline command that matches the above regular expression
     * @return string array of length 2, can be used directly to create a new deadline object
     * @throws DukeException if input cannot be parsed
     */
    public static String[] parseDeadline(String input) throws DukeException {
        try {
            String removeDeadlineKeyword = input.substring(9);
            int by = removeDeadlineKeyword.indexOf("/by");
            String task = removeDeadlineKeyword.substring(0, by).strip();
            String deadline = removeDeadlineKeyword.substring(by+3, removeDeadlineKeyword.length()).strip();
            return new String[] {task, deadline};
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Unexpected input format, could not parse deadline properly.");
        } catch (Exception e) {
            throw new DukeException("Unexpected error occured");
        }
    }

    /**
     * Processes string into useful information when command is identified as a event command
     * <p> Regular Expression: ^event\\s.+/from\\s.+/to\\s.+$
     * 
     * @param input event command that matches the above regular expression
     * @return string array of length 3, can be used directly to create a new deadline object
     * @throws DukeException if input cannot be parsed
     */
    public static String[] parseEvent(String input) throws DukeException {
        try {
            String removeEventKeyword = input.substring(6);
            int from = removeEventKeyword.indexOf("/from");
            int to = removeEventKeyword.indexOf("/to");
            String task = removeEventKeyword.substring(0, from).strip();
            String start = removeEventKeyword.substring(from+5, to).strip();
            String end = removeEventKeyword.substring(to+3, removeEventKeyword.length()).strip();
            return new String[] {task, start, end};
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Unexpected input format, could not parse event properly.");
        } catch (Exception e) {
            throw new DukeException("Unexpected error occured");
        }
    }

    /**
     * Processes string into useful information when command is identified as a todo command
     * <p> Regular Expression: ^todo\\s.+$
     * 
     * @param input todo command that matches the above regular expression
     * @return string that can be used directly to create a new deadline object
     * @throws DukeException if input cannot be parsed
     */
    public static String parseToDo(String input) throws DukeException {
        try {
            return input.substring(5, input.length()).strip();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Unexpected input format, could not parse todo properly.");
        } catch (Exception e) {
            throw new DukeException("Unexpected error occured");
        }
    }

    /**
     * Processes string into useful information when command is identified as a mark command
     * <p> Regular Expression: ^mark\\s\\d+$
     * 
     * @param input mark command
     * @return index to mark task
     * @throws DukeException if input cannot be parsed
     */
    public static int parseMark(String input) throws DukeException {
        try {
            int markIndex = Integer.valueOf(input.substring(5)) - 1; // account for 1 indexing
            return markIndex;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Unexpected input format, could not parse mark properly.");
        } catch (Exception e) {
            throw new DukeException("Unexpected error occured");
        }
    }

    /**
     * Processes string into useful information when command is identified as an unmark command
     * <p> Regular Expression: ^unmark\\s\\d+$
     * 
     * @param input unmark command
     * @return index to mark task
     * @throws DukeException if input cannot be parsed
     */
    public static int parseUnmark(String input) throws DukeException {
        try {
            int unmarkIndex = Integer.valueOf(input.substring(7)) - 1; // account for 1 indexing
            return unmarkIndex;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Unexpected input format, could not parse unmark properly.");
        } catch (Exception e) {
            throw new DukeException("Unexpected error occured");
        }
    }

    /**
     * Processes string into useful information when command is identified as a delete command
     * <p> Regular Expression: ^delete\\s\\d+$
     * 
     * @param input delete command
     * @return index to delete task
     * @throws DukeException if input cannot be parsed
     */
    public static int parseDelete(String input) throws DukeException {
        try {
            int deleteIndex = Integer.valueOf(input.substring(7)) - 1;
            return deleteIndex;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Unexpected input format, could not parse unmark properly.");
        } catch (Exception e) {
            throw new DukeException("Unexpected error occured");
        }
    }

    public static String parseFind(String input) throws DukeException {
        try {
            Pattern p = Pattern.compile("find\\s(.+)");
            Matcher m = p.matcher(input);
            m.find();
            return m.group(1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Unexpected input format, could not parse find properly.");
        } catch (Exception e) {
            throw new DukeException("Unexpected error occured");
        }

    }
}
