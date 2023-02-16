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
        String firstWord = inputLine.split(" ")[0];
        switch (firstWord) {
        case "deadline":
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty\n"
                + "(e.g. deadline do homework /31-12-2023 2359).");
        case "todo":
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty\n"
                + "(e.g. todo do homework).");
        case "event":
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty\n"
                + "(e.g. event do homework /01-12-2023 0000 /02-12-2023 2359).");
        case "delete":
            throw new DukeException("☹ OOPS!!! Please specify which task to delete (e.g. delete 1).");
        case "mark":
            throw new DukeException("☹ OOPS!!! Please specify which task to mark (e.g. mark 1).");
        case "unmark":
            throw new DukeException("☹ OOPS!!! Please specify which task to unmark (e.g. unmark 1).");
        case "find":
            throw new DukeException("☹ OOPS!!! Please specify which task to find (e.g. find homework).");
        case "tag":
            throw new DukeException("☹ OOPS!!! Please specify which task to tag (e.g. tag 1 #must do).");
        }
    }
}
