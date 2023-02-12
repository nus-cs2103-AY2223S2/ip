package pix.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import pix.commands.ByeCommand;
import pix.commands.Command;
import pix.commands.DeadlineCommand;
import pix.commands.DeleteCommand;
import pix.commands.EventCommand;
import pix.commands.FindCommand;
import pix.commands.HelpCommand;
import pix.commands.ListCommand;
import pix.commands.ListDateCommand;
import pix.commands.MarkCommand;
import pix.commands.ToDoCommand;
import pix.commands.UnmarkCommand;
import pix.data.MyData;
import pix.exceptions.PixException;

/**
 * Parser class to parse commands by the user.
 */
public class Parser {
    /** Data object containing list of tasks. */
    private final MyData data;

    /**
     * Constructs a Parser object.
     *
     * @param data Data object containing list of tasks.
     */
    public Parser(MyData data) {
        this.data = data;
    }

    /**
     * Parses bye command.
     *
     * @return ByeCommand object.
     */
    public Command parseBye() {
        return new ByeCommand();
    }

    /**
     * Parses list command.
     *
     * @return ListCommand object.
     */
    public Command parseList() {
        return new ListCommand();
    }

    /**
     * Parses listdate command.
     *
     * @param commandArr Array of user input split by a space character.
     * @return ListDateCommand object.
     * @throws PixException If no date given or if invalid date given.
     */
    public Command parseListDate(String[] commandArr) throws PixException {
        String listDateErrorMessage = "Please enter a date.\n\n e.g. listdate 'yyyy-MM-dd'";
        if (commandArr.length <= 1) {
            throw new PixException(listDateErrorMessage);
        }
        try {
            String dateString = commandArr[1];
            LocalDate date = LocalDate.parse(dateString);
            return new ListDateCommand(date);
        } catch (DateTimeParseException e) {
            throw new PixException(listDateErrorMessage);
        }
    }

    /**
     * Parses mark command.
     *
     * @param commandArr Array of user input split by a space character.
     * @return MarkCommand object.
     * @throws PixException If index not given or index not in valid range.
     */
    public Command parseMark(String[] commandArr) throws PixException {
        if (commandArr.length <= 1) {
            throw new PixException("Please enter an index to mark.");
        }
        try {
            int id = Integer.parseInt(commandArr[1]);
            boolean isOutOfBounds = id > data.len() || id < 0;
            if (isOutOfBounds) {
                throw new PixException("Please enter a valid number.");
            }
            return new MarkCommand(id - 1);
        } catch (NumberFormatException e) {
            throw new PixException(("Please enter an index to mark."));
        }
    }

    /**
     * Parses unmark command.
     *
     * @param commandArr Array of user input split by a space character.
     * @return UnmarkCommand object.
     * @throws PixException If index not given or index not in valid range.
     */
    public Command parseUnmark(String[] commandArr) throws PixException {
        if (commandArr.length <= 1) {
            throw new PixException("Please enter a index to unmark.");
        }
        try {
            int id = Integer.parseInt(commandArr[1]);
            boolean isOutOfBounds = id > data.len() || id < 0;
            if (isOutOfBounds) {
                throw new PixException("Please enter a valid number.");
            }
            return new UnmarkCommand(id - 1);
        } catch (NumberFormatException e) {
            throw new PixException(("Please enter an index to mark."));
        }
    }

    /**
     * Parses to do command.
     *
     * @param command Command entered by the user.
     * @return Add To Do object.
     * @throws PixException If no description given.
     */
    public Command parseTodo(String command) throws PixException {
        String description = removeCommand(command);
        if (description.isEmpty()) {
            throw new PixException("Cannot have an empty task.");
        }
        return new ToDoCommand(description);
    }

    /**
     * Parses deadline command.
     *
     * @param slashed Array of description and due date.
     * @return DeadlineCommand object.
     * @throws PixException If no description, due date or invalid format of date given.
     */
    public Command parseDeadline(String[] slashed) throws PixException {
        boolean isNotValidLength = slashed.length != 2;
        String deadlineErrorMessage = "Invalid format.\n\n"
                + "e.g. deadline 'description' / 'yyyy-MM-dd HH-mm'";
        if (isNotValidLength) {
            throw new PixException(deadlineErrorMessage);
        }
        String description = removeCommand(slashed[0]);
        String dueDate = removeCommand(slashed[1]);
        boolean isEmptyValue = description.isEmpty() || dueDate.isEmpty();
        if (isEmptyValue) {
            throw new PixException(deadlineErrorMessage);
        }
        try {
            return new DeadlineCommand(description, dueDate);
        } catch (DateTimeParseException e) {
            throw new PixException(deadlineErrorMessage);
        }
    }

    /**
     * Parses event command.
     *
     * @param slashed Array of description, eventStart date and eventEnd date.
     * @return EventCommand object.
     * @throws PixException If no description, eventStart date, eventEnd date, or invalid format of date given.
     */
    public Command parseEvent(String[] slashed) throws PixException {
        boolean isNotValidLength = slashed.length != 3;
        String eventErrorMessage = "Invalid format.\n\nFrom and To formatted as 'yyyy-MM-dd HH-mm'"
                + "\n\ne.g. event 'description' / 'From' / 'To'";
        if (isNotValidLength) {
            throw new PixException(eventErrorMessage);
        }
        String description = removeCommand(slashed[0]);
        String eventStart = removeCommand(slashed[1]);
        String eventEnd = removeCommand(slashed[2]);
        boolean isEmptyValue = description.isEmpty()
                || eventStart.isEmpty()
                || eventEnd.isEmpty();
        if (isEmptyValue) {
            throw new PixException(eventErrorMessage);
        }
        try {
            return new EventCommand(description, eventStart, eventEnd);
        } catch (DateTimeParseException e) {
            throw new PixException(eventErrorMessage);
        }
    }

    /**
     * Parses delete command.
     *
     * @param commandArr Array of user input split by a space character.
     * @return DeleteCommand object.
     * @throws PixException If index not given or index not in valid range.
     */
    public Command parseDelete(String[] commandArr) throws PixException {
        if (commandArr.length <= 1) {
            throw new PixException("Please enter an index to delete.");
        }
        try {
            int id = Integer.parseInt(commandArr[1]);
            boolean isOutOfBounds = id > data.len() || id < 0;
            if (isOutOfBounds) {
                throw new PixException("Please enter a valid number.");
            }
            return new DeleteCommand(id - 1);
        } catch (NumberFormatException e) {
            throw new PixException("Please enter a valid number.");
        }
    }

    /**
     * Parses find command.
     *
     * @param commandArr Array of user input split by a space character.
     * @return Tasks if keyword is in the description.
     * @throws PixException If no keyword is given.
     */
    public Command parseFind(String[] commandArr) throws PixException {
        if (commandArr.length <= 1) {
            throw new PixException("Please enter a keyword");
        }
        String keyword = commandArr[1];
        return new FindCommand(keyword);
    }

    public Command parseHelp() {
        return new HelpCommand();
    }

    /**
     * Removes the command word from user input.
     *
     * @param command Command that user inputs.
     * @return String without the command word.
     */
    public String removeCommand(String command) {
        String[] commandArr = command.split(" ");
        String[] descriptionArr = Arrays.copyOfRange(commandArr, 1, commandArr.length);
        return String.join(" ", descriptionArr);
    }

    /**
     * Parses the user input.
     *
     * @param command Command that the user inputs.
     * @return The corresponding command that is parsed.
     * @throws PixException If any errors related eventEnd formatting and indexing occurs.
     */
    public Command parse(String command) throws PixException {
        String[] commandArr = command.split(" ");
        String[] slashed = command.split("/");
        switch (commandArr[0]) {
        case "bye":
            return parseBye();
        case "ls":
            return parseList();
        case "lsd":
            return parseListDate(commandArr);
        case "mk":
            return parseMark(commandArr);
        case "unmk":
            return parseUnmark(commandArr);
        case "todo":
            return parseTodo(command);
        case "dline":
            return parseDeadline(slashed);
        case "event":
            return parseEvent(slashed);
        case "rm":
            return parseDelete(commandArr);
        case "find":
            return parseFind(commandArr);
        case "help":
            return parseHelp();
        default:
            throw new PixException("I am not sure what that means.");
        }
    }
}
