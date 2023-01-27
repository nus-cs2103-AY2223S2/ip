/**
 * A class to parse commands received from user input.
 */
public class Parser {
    /** Check for program exit (true when bye command entered).*/
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
    public Command parse(String commandStr) throws DukeException{
        switch (commandStr.split("\\s+")[0]) {
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
    public static int indexOf(String[] arr, String item) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    public boolean getIsExit() {
        return isExit;
    }
}
