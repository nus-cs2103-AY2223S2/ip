package duke.util;

import duke.Command;
import duke.DukeException;

public class Parser {

    /**
     * reads the command string and returns the command type
     * @param command string of the command
     * @return the Command type
     * @throws DukeException if the command does not exist
     */
    public static Command parseCommand(String command) throws DukeException {
        if (!Command.contains(command)) {
            throw new DukeException("Sorry! I don't know what that means!");
        }
        return Command.valueOf(command);
    }

    /**
     * parses the starting elements
     * @param elemString the string of commands
     * @return an array where each element in the array is a command
     */
    public static String[] parseStartingElements(String elemString) {
        return elemString.split(" ");
    }

    /**
     * parses a Todo statement
     * @param str the string to be parsed
     * @return the elements after the 'todo' substring, which should be empty
     */
    public static String parseTodo(String str) {
        return str.substring(4);
    }

    /**
     * parses a Deadline statement
     * @param str string to be parsed
     * @return the elements after the 'deadline' substring, segmented into an array
     */
    public static String[] parseDeadline(String str) {
        String temp = str.substring(8);

        return temp.split("/by");
    }

    /**
     * parses an event statement
     * @param str string to be parsed
     * @return the commands after 'event', segmented into an array
     * @throws DukeException if the format is wrong
     */
    public static String[] parseEvent(String str) throws DukeException {
        String temp = str.substring(5);
        String[] arr1 = temp.split("/from");
        if (arr1.length != 2) {
            throw new DukeException("I don't know what that means. Format it as 'event [do something] /from [start date] /to [end date]'");
        }
        String[] arr2 = arr1[1].split("/to");
        if (arr2.length != 2) {
            throw new DukeException("I don't know what that means. Format it as 'event [do something] /from [start date] /to [end date]'");
        }
        return new String[] {arr1[0], arr2[0], arr2[1]};
    }
}
