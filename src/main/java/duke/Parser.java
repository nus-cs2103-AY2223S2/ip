package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ErrorCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.StatisticsCommand;
import duke.command.UnmarkCommand;

/**
 * Parser class to convert to appriopriate types.
 */
public class Parser {

    /**
     * Formatter for String to LocalDateTime.
     */
    private static DateTimeFormatter strFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Formatter for LocalDateTime to String.
     */
    private static DateTimeFormatter ldtFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");


    /**
     * Formats a String to a Integer value.
     *
     * @param  index the index of the task the user wants to fetch.
     * @return an Integer.
     */
    public static Integer stringToInt(String index) {
        return Integer.parseInt(index);
    }

    /**
     * Converts the user input from a String to a Command class
     * to carry out the necessary actions.
     *
     * @param  command the users input.
     * @return Command to carry out the necessary actions.
     */
    public static Command stringToCommand(String command) {
        if (command.startsWith("delete")) {
            return new DeleteCommand(command);
        } else if (command.startsWith("mark")) {
            return new MarkCommand(command);
        } else if (command.startsWith("unmark")) {
            return new UnmarkCommand(command);
        } else if (command.equals("list")) {
            return new ListCommand(command);
        } else if (command.startsWith("todo") || command.startsWith("deadline") || command.startsWith("event")) {
            return new AddCommand(command);
        } else if (command.equals("bye")) {
            return new ExitCommand("bye");
        } else if (command.startsWith("find")) {
            return new FindCommand(command);
        } else if (command.equals("statistics")) {
            return new StatisticsCommand("statistics");
        } else {
            return new ErrorCommand("error");
        }
    }

    /**
     * Parses the Date and Time to LocalDateTime.
     *
     * @param  duration Date and Time given by the users.
     * @return localDateTime object.
     */
    public static LocalDateTime stringToDateTime(String duration) {
        LocalDateTime localDateTime = LocalDateTime.parse(duration, strFormatter);
        return localDateTime;
    }

    /**
     * Parses the LocalDateTime to Date and Time to Strings by following
     * a certain format: MMM dd yyyy HHmm.
     *
     * @param  localDateTime localDateTime recorded in the necessary task.
     * @return Strings of Date and Time for the various tasks.
     */
    public static String dateTimeToString(LocalDateTime localDateTime) {
        return localDateTime.format(ldtFormatter);
    }

    /**
     * Parses the LocalDateTime to Date and Time to Strings by following
     * a certain format: "yyyy-MM-dd HHmm". This is the format we will be
     * saving the tasks in our Storage.
     *
     * @param  localDateTime localDateTime recorded in the necessary task.
     * @return Strings of Date and Time for the various tasks.
     */
    public static String dateTimeSaving(LocalDateTime localDateTime) {
        return localDateTime.format(strFormatter);
    }
}
