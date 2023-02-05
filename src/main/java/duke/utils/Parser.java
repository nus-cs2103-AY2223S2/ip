package duke.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.commands.AddDeadlineCommand;
import duke.commands.AddEventCommand;
import duke.commands.AddTodoCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.UnmarkCommand;
import duke.exceptions.DukeException;


/**
 * Parser class to help understand the input lines.
 */
public class Parser {

    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


    /**
     * Gets the index that is the second argument for certain commands.
     *
     * @param str The string to be parsed.
     * @return The index that we want.
     * @throws DukeException When the string does not contain a number.
     */
    public static int returnIndexFromString(String str) throws DukeException {
        String trimmedStr = str.trim();
        if (!trimmedStr.chars().allMatch(Character::isDigit)) {
            throw new DukeException("Wrong format!");
        }
        return Integer.parseInt(trimmedStr) - 1;
    }

    /**
     * Prepares the Command for todo.
     *
     * @param args Arguments to be parsed.
     * @return The AddTodoCommand.
     */
    public static AddTodoCommand prepareAddTodo(String args) {
        return new AddTodoCommand(args, false);
    }

    /**
     * Prepares the Command for deadline.
     *
     * @param args The arguments to be parsed.
     * @return The AddDeadlineCommand.
     * @throws DukeException When the input string is in the wrong format.
     */
    public static AddDeadlineCommand prepareAddDeadline(String args) throws DukeException {
        final String[] arr = args.split(" /by ");
        if (arr.length != 2) {
            throw new DukeException("Wrong format. Correct format: 'deadline {do something} /by {end date time}.'");
        }

        LocalDateTime endDate;
        try {
            endDate = LocalDateTime.parse(arr[1], FORMATTER);
        } catch (DateTimeParseException e) {
            endDate = null;
        }

        if (endDate == null) {
            throw new DukeException("Datetime format is wrong. Please format as 'yyyy-MM-dd HH:mm'.");
        }
        return new AddDeadlineCommand(arr[0], false, endDate);
    }


    /**
     * Prepares the Command for event.
     *
     * @param args String to be parsed.
     * @return The AddEventCommand.
     * @throws DukeException When the input string is not in the correct format.
     */
    public static AddEventCommand prepareAddEvent(String args) throws DukeException {
        final String[] firstHalf = args.split(" /from ");
        if (firstHalf.length != 2) {
            throw new DukeException("Wrong format. Correct format: "
                    + "'event {some event} /from {start date time} /to {end date time}'");
        }
        final String[] secondHalf = firstHalf[1].split(" /to ");
        if (secondHalf.length != 2) {
            throw new DukeException("Wrong format. Correct format: "
                    + "'event {some event} /from {start date time} /to {end date time}'");
        }
        LocalDateTime startDate = null;
        LocalDateTime endDate = null;
        try {
            startDate = LocalDateTime.parse(secondHalf[0], FORMATTER);
            endDate = LocalDateTime.parse(secondHalf[1], FORMATTER);
        } catch (DateTimeParseException e) {
            startDate = null;
            endDate = null;
        }

        if (startDate == null && endDate == null) {
            throw new DukeException("DateTime format is wrong. Please format as 'yyyy-MM-dd HH:mm'.");
        }
        return new AddEventCommand(firstHalf[0], false, startDate, endDate);

    }

    /**
     * Parses the whole user input at first.
     *
     * @param userInput The user input string.
     * @return The Command being called.
     * @throws DukeException If the formatting is not right.
     */
    public static Command parse(String userInput) throws DukeException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new DukeException("Sorry, I don't understand you!");
        }
        String commandWord = matcher.group("commandWord").toLowerCase();
        String args = matcher.group("arguments");

        switch (commandWord) {
        case AddTodoCommand.COMMAND_WORD:
            if (args.equals("")) {
                throw new DukeException("Argument cannot be empty");
            }
            return prepareAddTodo(args);
        case AddDeadlineCommand.COMMAND_WORD:
            if (args.equals("")) {
                throw new DukeException("Argument cannot be empty");
            }
            return prepareAddDeadline(args);
        case AddEventCommand.COMMAND_WORD:
            if (args.equals("")) {
                throw new DukeException("Argument cannot be empty");
            }
            return prepareAddEvent(args);
        case DeleteCommand.COMMAND_WORD:
            if (args.equals("")) {
                throw new DukeException("Argument cannot be empty");
            }
            return new DeleteCommand(returnIndexFromString(args));
        case MarkCommand.COMMAND_WORD:
            if (args.equals("")) {
                throw new DukeException("Argument cannot be empty");
            }
            return new MarkCommand(returnIndexFromString(args));
        case UnmarkCommand.COMMAND_WORD:
            if (args.equals("")) {
                throw new DukeException("Argument cannot be empty");
            }
            return new UnmarkCommand(returnIndexFromString(args));
        case FindCommand.COMMAND_WORD:
            if (args.equals("")) {
                throw new DukeException("Argument cannot be empty");
            }
            return new FindCommand(args.trim());
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        default:
            throw new DukeException("Sorry, I don't understand you.");
        }
    }

}
