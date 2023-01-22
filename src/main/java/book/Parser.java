package book;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import book.command.AddCommand;
import book.command.Command;
import book.command.DeleteCommand;
import book.command.ExitCommand;
import book.command.FindCommand;
import book.command.ListCommand;
import book.command.MarkCommand;
import book.command.UnmarkCommand;
import book.exception.IncompleteInputException;
import book.exception.InvalidFormatException;
import book.exception.InvalidInputException;
import book.task.Deadline;
import book.task.Event;
import book.task.ToDo;

/**
 * {@code static} class for parsing input to {@code Book} by splitting input and returning the
 * relevant {@code Command} initialized with any additional arguments.
 */
public final class Parser {
    /**
     * Parses {@code String} input and returns the relevant {@code Command}.
     * @param command {@code String} input to be parsed and returned as a {@code Command}.
     * @return {@code Command} parsed from the {@code String} input, initialized with relevant
     *     additional arguments.
     * @throws IncompleteInputException if {@code String} input is incomplete for processing a
     *     {@code Command}.
     * @throws InvalidFormatException if {@code String} input provided has invalid format.
     * @throws InvalidInputException if {@code String} input provided does not match any
     *     {@code Command}.
     */
    public static Command parse(String command) throws IncompleteInputException,
            InvalidFormatException, InvalidInputException {
        try {
            String[] inputs = command.split(" ", 2);
            switch (inputs[0]) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "mark":
                return new MarkCommand(Integer.parseInt(inputs[1]));
            case "unmark":
                return new UnmarkCommand(Integer.parseInt(inputs[1]));
            case "delete":
                return new DeleteCommand(Integer.parseInt(inputs[1]));
            case "find":
                return new FindCommand(inputs[1]);
            case "todo":
                return new AddCommand(new ToDo(inputs[1]));
            case "deadline":
                String[] deadlineDetails = inputs[1].split("/by ", 2);
                return new AddCommand(new Deadline(deadlineDetails[0],
                        parseDateTime(deadlineDetails[1])));
            case "event":
                String[] eventDetails = inputs[1].split("/from | /to ", 3);
                return new AddCommand(new Event(eventDetails[0], parseDateTime(eventDetails[1]),
                        parseDateTime(eventDetails[2])));
            default:
                throw new InvalidInputException("Sorry, this command is not in Book's dictionary.");
            }
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new IncompleteInputException("This command is missing details");
        } catch (DateTimeParseException exception) {
            throw new InvalidFormatException("Please use the format dd/MM/yy-HHmm");
        }
    }

    /**
     * Parses {@code String} input representing Date/Time and returns the corresponding
     * {@code LocalDateTime}.
     * @param dateTime {@code String} input representing Date/Time to be parsed.
     * @return {@code LocalDateTime} corresponding to the given {@code String} input.
     * @throws DateTimeParseException if an error occurs when parsing the {@code String} input.
     */
    public static LocalDateTime parseDateTime(String dateTime) throws DateTimeParseException {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yy-HHmm");
        return LocalDateTime.parse(dateTime, format);
    }
}
