package task;


import duke.*;

/**
 * Parser class that handles user input.
 */
public class Parser {
    /**
     * Checks whether the input is empty or not.
     *
     * @param input The user input.
     * @param command The corresponding command we check the input against.
     * @throws DukeException When the input is empty.
     */
    public void checkEmpty(String input, String command) throws DukeException {
        if (input.length() < command.length() + 2) {
            switch (command) {
            case "todo":
                throw new EmptyTodoException();
            case "deadline":
                throw new EmptyDeadlineException();
            case "event":
                throw new EmptyEventException();
            case "mark": case "unmark": case "delete": case "find":
                throw new EmptyActionException();
            }
        }
    }

    /**
     * Check whether the user input is valid or not.
     *
     * @param input The user input
     * @param command The corresponding command we check the input against.
     * @throws DukeException When the input does not follow the standard format.
     */
    public void checkInvalidInput(String input, String command) throws DukeException {
        switch (command) {
        case "todo":
            throw new InvalidTodoException();
        case "deadline":
            throw new InvalidDeadlineException();
        case "event":
            throw new InvalidEventException();
        case "mark": case "unmark": case "delete": case "find":
            throw new InvalidActionInput();
        }
    }

    /**
     * Coverts enum into string.
     *
     * @param c Enum command given.
     * @return Lowercase form of the enum.
     */
    public String convertEnum(Command c) {
        String res = "";
        switch (c) {
        case LIST: case MARK: case TODO: case EVENT: case FIND:
        case DELETE: case UNMARK: case DEADLINE: case BYE:
            res = c.name().toLowerCase();
        }
        return res;
    }
}
