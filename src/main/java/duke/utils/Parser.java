package duke.utils;
import duke.Command;
import duke.DukeException;

/**
 * Parser class to help understand the input lines.
 */
public class Parser {

    /**
     * Parses command.
     *
     * @param command String to be parsed.
     * @return The duke.Command type.
     */
    public static Command parseCommand(String command) throws DukeException {
        if (!Command.contains(command)) {
            throw new DukeException("Sorry! I don't know what that means!");
        }
        return Command.valueOf(command);
    }

    /**
     * Parses starting elements.
     *
     * @param elemString String to be parsed.
     * @return An array of individual commands.
     */
    public static String[] parseStartingElements(String elemString) {

        return elemString.split(" ");
    }

    /**
     * Parses todo.
     *
     * @param str String to be parsed.
     * @return An array of individual commands.
     */
    public static String parseTodo(String str) {

        return str.substring(4);
    }

    /**
     * Parses deadline.
     *
     * @param str String to be parsed.
     * @return An array of individual commands.
     */
    public static String[] parseDeadline(String str) {
        String temp = str.substring(8);

        return temp.split(" /by ");
    }


    /**
     * Parses an event statement.
     *
     * @param str String to be parsed.
     * @return The commands after 'event', segmented into an array.
     * @throws DukeException If the format is wrong.
     */
    public static String[] parseEvent(String str) throws DukeException {
        String temp = str.substring(5);
        String[] arr1 = temp.split(" /from ");
        if (arr1.length != 2) {
            throw new DukeException("I don't know what that means. "
                    + "Format it as 'event [do something] /from [start date] /to [end date]'");
        }
        String[] arr2 = arr1[1].split(" /to ");
        if (arr2.length != 2) {
            throw new DukeException("I don't know what that means. "
                    + "Format it as 'event [do something] /from [start date] /to [end date]'");
        }
        return new String[] {arr1[0], arr2[0], arr2[1]};
    }
}
