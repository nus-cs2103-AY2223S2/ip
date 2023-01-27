package duke.util;
import duke.Command;
import duke.DukeException;

public class Parser {

    /**
     * Reads the command string and returns the command type.
     *
     * @param command String of the command.
     * @return The Command type.
     * @throws DukeException If the command does not exist.
     */
    public static Command parseCommand(String command) throws DukeException {
        if (!Command.isCommand(command)) {
            throw new DukeException("Sorry! I don't know what that means!");
        }
        return Command.valueOf(command);
    }

    /**
     * Parses the starting elements.
     *
     * @param elemString The string of commands.
     * @return An array where each element in the array is a command.
     */
    public static String[] parseStartingElements(String elemString) {
        return elemString.split(" ");
    }

    /**
     * Parses a Todo statement.
     *
     * @param str The string to be parsed.
     * @return The elements after the 'todo' substring, which should be empty.
     */
    public static String parseTodo(String str) {
        return str.substring(4);
    }

    /**
     * Parses a Deadline statement.
     *
     * @param str String to be parsed.
     * @return The elements after the 'deadline' substring, segmented into an array.
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
            throw new DukeException("I don't know what that means. Format it as 'event [do something] /from [start date] /to [end date]'");
        }
        String[] arr2 = arr1[1].split(" /to ");
        if (arr2.length != 2) {
            throw new DukeException("I don't know what that means. Format it as 'event [do something] /from [start date] /to [end date]'");
        }
        return new String[] {arr1[0], arr2[0], arr2[1]};
    }
}
