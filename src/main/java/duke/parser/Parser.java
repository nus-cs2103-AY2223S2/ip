package duke.parser;

import duke.Formatter;
import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeException;
import duke.exception.IncompleteDescException;
import duke.exception.InvalidInputException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Encapsulation of a parser that parses the inputs given.
 */
public class Parser {
    protected enum CommandEnum {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE
    }

    /**
     * Instantiates a new ToDo task and returns it.
     * @param splitInputs An array containing the input by the user.
     * @return Returns the new ToDo task.
     * @throws DukeException If name of the task is not given.
     */
    private static ToDo getTodo(String[] splitInputs) throws DukeException {
        if (splitInputs.length <= 1 || splitInputs[1].isBlank()) {
            throw new IncompleteDescException(
                    "The description of a todo cannot be empty!");
        }
        return new ToDo(splitInputs[1]);
    }

    /**
     * Parses the given string into a LocalDate object.
     * @param date The given string representation of the date to be parsed.
     * @return Returns the parsed LocalDate.
     */
    public static LocalDate parseDate(String date) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return LocalDate.parse(date, dtf);
    }


    /**
     * Instantiate a new Deadline object and returns it.
     * @param splitInputs The input given by the user.
     * @return Returns the new Deadline object.
     * @throws DukeException If name or due date/time is not provided,
     *                       or due date/time is given in the wrong format.
     */
    private static Deadline getDeadline(String[] splitInputs) throws DukeException {
        if (splitInputs.length <= 1) {
            throw new IncompleteDescException(
                    "The description of a deadline cannot be empty!");
        }
        int endIndex = splitInputs[1].indexOf("/by");
        if (endIndex < 0) {
            throw new IncompleteDescException(
                    "Please add the due date/time!");
        }
        String name = splitInputs[1].substring(0, endIndex).strip();
        String end = splitInputs[1].substring((endIndex + 3)).strip();
        if (name.isBlank()) {
            throw new IncompleteDescException(
                    "The description of a deadline cannot be empty!");
        }
        if (end.isBlank()) {
            throw new IncompleteDescException(
                    "Please add the due date/time!");
        }
        try {
            LocalDate endLd = Parser.parseDate(end);
            end = Formatter.formatDateForPrint(endLd);
            if (endLd.isBefore(LocalDate.now())) {
                throw new InvalidInputException(
                        "The given deadline (yyyy/mm/dd) " + end + " has passed.");
            }
            return new Deadline(name, endLd);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException(
                    "Please enter the date/time in \"yyyy/mm/dd\" format.");
        }
    }

    /**
     * Instantiate a new Event object and returns it.
     * @param splitInputs The input given by the user.
     * @return Returns the new Event.
     * @throws DukeException If name or start date/time or due date/time is not given
     *                       or the date/time is given in the wrong format.
     */
    private static Event getEvent(String[] splitInputs) throws DukeException {
        if (splitInputs.length <= 1) {
            throw new IncompleteDescException(
                    "The description of an event cannot be empty!");
        }
        int startIndex = splitInputs[1].indexOf("/from");
        int endIndex = splitInputs[1].indexOf("/to");
        if (startIndex < 0 || endIndex < 0) {
            throw new IncompleteDescException(
                    "Please make sure that the start and end date/time are not empty!");
        }
        String name = splitInputs[1].substring(0, startIndex).strip();
        String start = splitInputs[1].substring((startIndex + 5), endIndex).strip();
        String end = splitInputs[1].substring(endIndex + 3).strip();
        if (name.isBlank()) {
            throw new IncompleteDescException(
                    "The description of an event cannot be empty!");
        }
        if (start.isBlank() || end.isBlank()) {
            throw new IncompleteDescException(
                    "Please make sure that the start and end date/time are not empty!");
        }
        try {
            LocalDate startLd = Parser.parseDate(start);
            LocalDate endLd = Parser.parseDate(end);
            if (startLd.isAfter(endLd)) {
                throw new InvalidInputException(
                        "The start date (yyyy/mm/dd) should be before the end date (yyyy/mm/dd).");
            }
            if (endLd.isBefore(LocalDate.now())) {
                end = Formatter.formatDateForPrint(endLd);
                throw new InvalidInputException(
                        "The given event (end date: " + end + ") has ended.");
            }
            return new Event(name, startLd, endLd);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException(
                    "Please enter the date/time in \"yyyy/mm/dd\" format.");
        }
    }

    /**
     * Parses the given string into the correct command.
     * @param input The string input given by the user.
     * @return Returns a Command representing the command given.
     * @throws DukeException if command is incomplete or invalid.
     */
    public static Command parseCommand(String input) throws DukeException {
        String[] splitInputs = input.split(" ", 2);
        CommandEnum commandType;
        try {
            commandType = CommandEnum.valueOf(splitInputs[0].toUpperCase());
            switch (commandType) {
            case BYE:
                return new ByeCommand();
            case LIST:
                return new ListCommand();
            case MARK:
                if (splitInputs.length <= 1) {
                    throw new InvalidInputException(
                            "Please give the index of the item to be marked.");
                }
                return new MarkCommand(Integer.parseInt(splitInputs[1]) - 1);
            case UNMARK:
                if (splitInputs.length <= 1) {
                    throw new InvalidInputException(
                            "Please give the index of the item to be unmarked.");
                }
                return new UnmarkCommand(Integer.parseInt(splitInputs[1]) - 1);
            case DELETE:
                if (splitInputs.length <= 1) {
                    throw new InvalidInputException(
                            "Please give the index of the item to be deleted.");
                }
                return new DeleteCommand(Integer.parseInt(splitInputs[1]) - 1);
            case TODO:
                Task task = Parser.getTodo(splitInputs);
                return new AddCommand(task);
            case DEADLINE:
                Deadline deadline = Parser.getDeadline(splitInputs);
                return new AddCommand(deadline);
            case EVENT:
                Event event = Parser.getEvent(splitInputs);
                return new AddCommand(event);
            default:
                throw new InvalidInputException(
                        "OPPS! I'm sorry, there is no such command.\nPlease try again.");
            }
        } catch (IllegalArgumentException i) {
            throw new InvalidInputException(
                    "OPPS! I'm sorry, there is no such command.\nPlease try again.");
        }
    }
}
