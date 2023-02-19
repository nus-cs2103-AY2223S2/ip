package ui;

import static java.util.Map.entry;

import duke.DateFormatDukeException;
import duke.DukeException;
import duke.IncompleteCommandDukeException;
import duke.NotRecognizedCommandDukeException;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Map;

import command.Command;

/**
 * A string parser that processes user-input commands.
 */
public class Parser {
    private static final String RUNNING_DIRECTORY = "ip";

    private static Map<String, String> stringToCommandClass = Map.ofEntries(
            entry("list", "ListCommand"),
            entry("mark", "MarkCommand"),
            entry("unmark", "UnmarkCommand"),
            entry("delete", "DeleteCommand"),
            entry("todo", "TodoCommand"),
            entry("event", "EventCommand"),
            entry("deadline", "DeadlineCommand"),
            entry("bye", "ExitCommand"),
            entry("find", "FindCommand")
    );

    /**
     * * Parse the user-input date string into a date object
     * @param dateString the string representation of the date
     * @return the parsed date object
     * @throws DukeException when the date cannot be parsed due to format issues
     */
    public static LocalDate parseDate(String dateString) throws DukeException {
        try {
            return LocalDate.parse(dateString);
        } catch (DateTimeParseException e) {
            throw new DateFormatDukeException();
        }
    }

    /**
     * Returns the appropriate command object based on user-input command string
     * @param command the user-input string
     * @param suppressPrint whether to suppress print-out or not
     * @return the command object
     * @throws DukeException when any error occurs
     */
    public static Command parseCommand(String command, boolean suppressPrint) throws DukeException {
        Object object = null;
        assert getCurrentDirectoryName().equals(RUNNING_DIRECTORY)
                : "You should run the program in the Main class folder";

        try {
            String commandName = stringToCommandClass.get(command.split(" ")[0]);
            Class<?> c = Class.forName("command." + commandName);
            Constructor<?> cons = c.getConstructor(String.class, boolean.class);
            object = cons.newInstance(command, !suppressPrint);
        } catch (ArrayIndexOutOfBoundsException e) {
            /* the split command list is a null array */
            throw new IncompleteCommandDukeException("Please ensure the first word is a command name. ");
        } catch (ClassNotFoundException e) {
            /* Unknown command, such as null */
            throw new NotRecognizedCommandDukeException();
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException e) {
            /* Unknown constructor */
            throw new DukeException(e.toString());
        } catch (InvocationTargetException e) {
            /* Runtime error, such as parsing error */
            Throwable t = e.getTargetException();
            throw new DukeException(t.toString());
        }
        return (Command) object;
    }

    /**
     * Returns the name of the current directory.
     * @return current directory name
     */
    public static String getCurrentDirectoryName() {
        return new File(System.getProperty("user.dir")).getName();
    }
}
