package duke;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.TodoCommand;
import duke.command.UnmarkCommand;

/**
 * A class to parse commands received from user input.
 */
public class Parser {
    // Check for program exit (true when bye command entered).
    private boolean isExit;

    /**
     * Constructor for Parser class.
     */
    public Parser() {
        isExit = false;
    }

    /**
     * Parses a command received from input.
     * @param commandStr command to be parsed.
     * @return The Command obtained from parsing.
     * @throws DukeException If command input is invalid.
     */
    public Command parse(String commandStr) throws DukeException {
        switch (commandStr.split(Values.SPACEX)[0]) {
        case "list":
            return new ListCommand();
        case "todo":
            return new TodoCommand();
        case "deadline":
            return new DeadlineCommand();
        case "event":
            return new EventCommand();
        case "mark":
            return new MarkCommand();
        case "unmark":
            return new UnmarkCommand();
        case "delete":
            return new DeleteCommand();
        case "find":
            return new FindCommand();
        case "bye":
            isExit = true;
            return new ByeCommand();
        default:
            throw new DukeException("Invalid command");
        }
    }

    /**
     * Utility function to find index of element in a String array.
     * @param arr The array to search.
     * @param item The item to find.
     * @return Index of item if present; -1 otherwise.
     */
    public static int getIndexOf(String[] arr, String item) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Gets whether the program needs to exit (has received a 'bye' command).
     * @return true if program must exit now; false otherwise.
     */
    public boolean getIsExit() {
        return isExit;
    }
}
