package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Encapsulates a parser which parses user commands and makes sense of it.
 *
 * @author Sean Chin Jun Kai
 */
public class Parser {
    private static String[] args;
    private static Command action;

    /**
     * Parses the userInput to retrieve the main command and corresponding arguments.
     *
     * @param userInput String that user inputs.
     * @throws DukeException for invalid arguments.
     */
    public static void parseUserResponse(String userInput) throws DukeException {
        String[] tokens = userInput.split(" ", 2);
        String command = tokens[0];

        switch (command) {
        case "bye": {
            if (tokens.length != 1) {
                throw new DukeException("Invalid number of arguments.Only require 1!");
            }
            action = Command.BYE;
            break;
        }
        case "list": {
            if (tokens.length != 1) {
                throw new DukeException("Invalid number of arguments.Only require 1!");
            }
            action = Command.LIST;
            break;
        }
        case "find": {
            if (tokens.length < 2) {
                throw new DukeException("Keyword missing!");
            }
            action = Command.FIND;
            args = tokens;
            break;
        }
        case "unmark": {
            if (tokens.length < 2) {
                throw new DukeException("Task number missing!");
            }
            action = Command.UNMARK;
            args = tokens;
            break;

        }
        case "mark": {
            if (tokens.length < 2) {
                throw new DukeException("Task number missing!");
            }
            action = Command.MARK;
            args = tokens;
            break;
        }
        case "delete": {
            if (tokens.length < 2) {
                throw new DukeException("Task number missing!");
            }
            action = Command.DELETE;
            args = tokens;
            break;
        }
        case "todo": {
            if (tokens.length < 2) {
                throw new DukeException("The description of a todo cannot be empty!");
            }
            action = Command.TODO;
            args = tokens;
            break;
        }
        case "deadline": {
            if (tokens.length < 2) {
                throw new DukeException("The description of a deadline cannot be empty!");
            }
            action = Command.DEADLINE;
            args = tokens;
            break;
        }
        case "event": {
            if (tokens.length < 2) {
                throw new DukeException("The description of a event cannot be empty!");
            }
            action = Command.EVENT;
            args = tokens;
            break;
        }
        default:
            throw new DukeException("Invalid command entered!");
        }
    }


    /**
     * Parses the stored arguments and returns a Todo object.
     *
     * @return Todo object.
     * @throws DukeException if description of Todo is not present.
     */
    public static Todo parseTodo() throws DukeException {
        if (args.length < 2) {
            throw new DukeException("The description of a todo cannot be empty!");
        }
        String desc = args[1];
        return new Todo(desc);
    }


    /**
     * Parses the stored arguments and returns a Deadline object.
     *
     * @return Deadline object.
     * @throws DukeException if end date/time not present or wrong format of end date/time.
     */
    public static Deadline parseDeadline() throws DukeException {
        String[] separated = args[1].split("/by ");

        if (separated.length < 2) {
            throw new DukeException("Deadline needs to include a specific end date!");
        }
        try {
            LocalDate date = LocalDate.parse(separated[1]);
            return new Deadline(separated[0], date);
        } catch (DateTimeParseException e) {
            throw new DukeException("Give deadline in YYYY-MM-DD format!");
        }
    }


    /**
     * Parses the stored arguments and returns an Event object.
     *
     * @return Event object.
     * @throws DukeException if start and end date/time not present.
     */
    public static Event parseEvent() throws DukeException {
        String[] separated = args[1].split("/from |/to ");

        if (separated.length < 3) {
            throw new DukeException("Event needs to include a start date/time and a end date/time!");
        }
        return new Event(separated[0], separated[1], separated[2]);
    }

    /**
     * Parses the stored arguments to get referenced Task ID.
     *
     * @return Task ID.
     * @throws DukeException if task ID is of the wrong type.
     */
    public static int parseTask() throws DukeException {
        try {
            return Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("Given task number is invalid!");
        }
    }

    public static Command getCommand() {
        return action;
    }

    public static String[] getArgs() {
        return args;
    }
}
