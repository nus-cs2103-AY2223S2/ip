package task;


import duke.*;

import java.util.Objects;

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
     * @param userInput The user input
     * @param command The corresponding command we check the input against.
     * @throws DukeException When the input does not follow the standard format.
     */
    public void checkInvalidInput(String userInput, String command) throws DukeException {
        String[] expressions = userInput.split(" ");
        if (Objects.equals(command, "deadline")) {
            if (!userInput.contains("/by")) {
                throw new InvalidDeadlineException();
            }
            if ("deadline".length() + 1 == userInput.indexOf("/by")) {
                throw new InvalidDeadlineException();
            }
            if (userInput.indexOf("/by") + "/by".length() == userInput.length()) {
                throw new InvalidDeadlineException();
            }
        } else if (Objects.equals(command, "event")) {
            if ((!userInput.contains("/from")) || (!userInput.contains("/to"))) {
                throw new InvalidEventException();
            }
            if ("event".length() + 1 == userInput.indexOf("/from")) {
                throw new InvalidEventException();
            } else if (userInput.indexOf("/from") + "/from".length() + 1 == userInput.indexOf("/to")) {
                throw new InvalidEventException();
            } else if (userInput.indexOf("/to") + "/to".length() == userInput.length()) {
                throw new InvalidEventException();
            }
        } else if (Objects.equals(command, "mark") || Objects.equals(command, "unmark")
                || Objects.equals(command, "delete")) {
            if (expressions.length != 2) {
                throw new InvalidActionInput();
            }
            try {
                int taskNumber = Integer.parseInt(expressions[1]);
            } catch (NumberFormatException exc) {
                throw new InvalidActionInput();
            }
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

    public boolean isCommand(String userInputCommand, Command command) {
        return userInputCommand.equals(convertEnum(command));
    }
}
