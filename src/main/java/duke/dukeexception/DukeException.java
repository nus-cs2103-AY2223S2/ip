package duke.dukeexception;

/**
 * Class that handles exceptions unique to the duke.Duke application
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
    /**
     * Checks the validity of user input
     * @param inputLine a line of command entered by the user, to be checked if it is valid
     * @throws DukeException throws a customised exception message if the command input is not valid
     */
    public static void checkInput(String inputLine) throws DukeException {
        if (inputLine.equals("deadline")) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        } else if (inputLine.equals("todo")) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        } else if (inputLine.equals("event")) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        } else if (inputLine.equals("delete")) {
            throw new DukeException("☹ OOPS!!! Please specify which task to delete.");
        }
    }
}
