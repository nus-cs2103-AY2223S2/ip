package ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static java.util.Map.entry;

import command.Command;
import command.CommandClass;
import duke.DukeException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * A string parser that processes user-input commands.
 */
public class Parser {
    private static Map<String, String> stringToCommandClass = Map.ofEntries(
            entry("list", "ListCommand"),
            entry("mark", "MarkCommand"),
            entry("unmark", "UnmarkCommand"),
            entry("delete", "DeleteTaskCommand"),
            entry("todo", "TaskCommand"),
            entry("event", "TaskCommand"),
            entry("deadline", "TaskCommand"),
            entry("bye", "ExitCommand"),
            entry("find", "FindTaskCommand")
    );

    /**
     * Gets the content of the command
     *
     * @param string  the command string
     * @param command a command type
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
     * @param string  the input string from the user
     * @param command a candidate command to check against
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
        case FIND:
            isCommand = string.toUpperCase().startsWith(command.name());
            break;
        default:
            break;
        }
        return isCommand;
    }

    /**
     * Parse the user-input date string into a date object
     *
     * @param dateString the string representation of the date
     * @return the date object
     */
    public static LocalDate parseDate(String dateString) throws DukeException {
        try {
            return LocalDate.parse(dateString);
        } catch (DateTimeParseException e) {
            throw new DukeException("The date could not be parsed!");
        }
    }

    /**
     * Returns the appropriate command object based on user-input command string
     * @param command the user-input string
     * @param suppressPrint whether to suppress print-out or not
     * @return the command object
     * @throws DukeException when any error occurs
     */
    public static CommandClass parseCommand(String command, boolean suppressPrint) throws DukeException {
        try {
            Class<?> c = Class.forName("command." + stringToCommandClass.get(command.split(" ")[0]));
            Constructor<?> cons = c.getConstructor(String.class, boolean.class);
            Object object = cons.newInstance(command, !suppressPrint);
            return (CommandClass) object;
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException
                 | InvocationTargetException e) {
            throw new DukeException(e.toString());
        }
    }
}
