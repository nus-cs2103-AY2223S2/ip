package logic.parser;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import exceptions.DukeException;
import logic.commands.Command;
import logic.commands.ByeCommand;
import logic.commands.DeadlineCommand;
import logic.commands.DeleteCommand;
import logic.commands.DoAfterCommand;
import logic.commands.EventCommand;
import logic.commands.FindCommand;
import logic.commands.HelpCommand;
import logic.commands.InvalidCommand;
import logic.commands.ListCommand;
import logic.commands.MarkCommand;
import logic.commands.TodoCommand;
import logic.commands.UnmarkCommand;

/**
 * Class that parses the inputs given by the user
 */
public class Parser {

    public static Command parse(String input) throws DukeException {
        String[] command = input.split(" ");
        String commandType = command[0];
        String[] args = Arrays.copyOfRange(command, 1, command.length);

        // make commandType uppercase
        commandType = commandType.toUpperCase();
        switch (commandType) {
            case "TODO":
                TodoCommand.validate(args);
                return new TodoCommand(args);
            case "DEADLINE":
                DeadlineCommand.validate(args);
                return new DeadlineCommand(args);
            case "EVENT":
                EventCommand.validate(args);
                return new EventCommand(args);
            case "DOAFTER":
                DoAfterCommand.validate(args);
                return new DoAfterCommand(args);
            case "MARK":
                MarkCommand.validate(args);
                return new MarkCommand(args);
            case "UNMARK":
                UnmarkCommand.validate(args);
                return new UnmarkCommand(args);
            case "DELETE":
                return new DeleteCommand(args);
            case "FIND":
                FindCommand.validate(args);
                return new FindCommand(args);
            case "HELP":
                return new HelpCommand();
            case "LIST":
                return new ListCommand();
            case "BYE":
                return new ByeCommand();
            default:
                return new InvalidCommand();
        }
    }

    /**
     * Splits the array into two parts
     *
     * @param array     The array to be split
     * @param delimiter The delimiter to split the array
     * @return The split array
     */
    public static List<String> splitArray(String[] array, String delimiter) {
        StringBuilder sb = new StringBuilder();
        List<String> res = new ArrayList<String>();

        for (String str : array) {
            if (str.equalsIgnoreCase(delimiter)) {
                res.add(sb.toString().trim());
                sb = new StringBuilder();
            } else {
                sb.append(str);
                sb.append(" ");
            }
        }
        res.add(sb.toString().trim());
        return res;
    }

    /**
     * Checks if the string is a valid datetime
     *
     * @param str    The string to be checked
     * @param format The format of the datetime
     * @return True if the string is a valid datetime, false otherwise
     */
    public static boolean isValidDatetime(String str, String format) {
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
            dtf.parse(str);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}
