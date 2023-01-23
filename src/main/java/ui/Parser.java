package ui;

import command.Command;

import duke.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * A string parser that processes user-input commands.
 */
public class Parser {
    /**
     * Gets the content of the command
     *
     * @param string:  the command string
     * @param command: a command type
     * @return the content of the command
     * @throws DukeException when the string is not complete
     */
    public String getCommandContent(String string, Command command) throws DukeException {
        String commandString = command.name().toLowerCase();
        if ((!commandString.equals("list")) && string.length() <= commandString.length() + 1) {
            throw new DukeException("The command argument is not complete.");
        }
        return string.substring(string.indexOf(commandString) + commandString.length() + " ".length());
    }

    /**
     * Returns whether the input string is of the specified command type
     *
     * @param string:  the input string from the user
     * @param command: a candidate command to check against
     * @return whether the input string is of the specified command type
     */
    public boolean checkCommand(String string, Command command) {
        boolean isCommand = false;
        switch (command) {
        case LIST:
            isCommand = string.equalsIgnoreCase("list");
            break;
        case MARK:
        case UNMARK:
        case TODO:
        case DEADLINE:
        case EVENT:
        case DELETE:
            isCommand = string.toUpperCase().startsWith(command.name());
            break;
        }
        return isCommand;
    }

    /**
     * Parse the user-input date string into a date object
     *
     * @param dateString: the string representation of the date
     * @return the date object
     */
    public static LocalDate parseDate(String dateString) throws DukeException {
        try {
            return LocalDate.parse(dateString);
        } catch (DateTimeParseException e) {
            throw new DukeException("The date could not be parsed!");
        }
    }
}
