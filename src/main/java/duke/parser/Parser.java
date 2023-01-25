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
     * @param inputArr An array containing the input by the user.
     * @return Returns the new ToDo task.
     * @throws DukeException If name of the task is not given.
     */
    private static ToDo getTodo(String[] inputArr) throws DukeException {
        if (inputArr.length <= 1 || inputArr[1].isBlank()) {
            throw new IncompleteDescException("The description of a todo cannot be empty!");
        }
        return new ToDo(inputArr[1]);
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
     * @param inputArr The input given by the user.
     * @return Returns the new Deadline object.
     * @throws DukeException If name or due date/time is not provided,
     *                       or due date/time is given in the wrong format.
     */
    private static Deadline getDeadline(String[] inputArr) throws DukeException {
        if (inputArr.length <= 1) {
            throw new IncompleteDescException("The description of a deadline cannot be empty!");
        }
        int endIndex = inputArr[1].indexOf("/by");
        if (endIndex < 0) {
            throw new IncompleteDescException("Please add the due date/time!");
        }
        String name = inputArr[1].substring(0, endIndex).strip();
        String end = inputArr[1].substring((endIndex + 3)).strip();
        if (name.isBlank()) {
            throw new IncompleteDescException("The description of a deadline cannot be empty!");
        }
        if (end.isBlank()) {
            throw new IncompleteDescException("Please add the due date/time!");
        }
        try {
            LocalDate ldEnd = Parser.parseDate(end);
            end = Formatter.formatDatePrint(ldEnd);
            if (ldEnd.isBefore(LocalDate.now())) {
                throw new InvalidInputException("The given deadline (yyyy/mm/dd) " + end + " has passed.");
            }
            return new Deadline(name,ldEnd);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException("Please enter the date/time in \"yyyy/mm/dd\" format.");
        }
    }

    /**
     * Instantiate a new Event object and returns it.
     * @param inputArr The input given by the user.
     * @return Returns the new Event.
     * @throws DukeException If name or start date/time or due date/time is not given
     *                       or the date/time is given in the wrong format.
     */
    private static Event getEvent(String[] inputArr) throws DukeException {
        if (inputArr.length <= 1) {
            throw new IncompleteDescException("The description of an event cannot be empty!");
        }
        int startIndex = inputArr[1].indexOf("/from");
        int endIndex = inputArr[1].indexOf("/to");
        if (startIndex < 0 || endIndex < 0) {
            throw new IncompleteDescException(
                    "Please make sure that the start and end date/time are not empty!");
        }
        String name = inputArr[1].substring(0, startIndex).strip();
        String start = inputArr[1].substring((startIndex + 5), endIndex).strip();
        String end = inputArr[1].substring(endIndex + 3).strip();
        if (name.isBlank()) {
            throw new IncompleteDescException("The description of an event cannot be empty!");
        }
        if (start.isBlank() || end.isBlank()) {
            throw new IncompleteDescException(
                    "Please make sure that the start and end date/time are not empty!");
        }
        try {
            LocalDate ldStart = Parser.parseDate(start);
            LocalDate ldEnd = Parser.parseDate(end);
            if (ldStart.isAfter(ldEnd)) {
                throw new InvalidInputException(
                        "The start date (yyyy/mm/dd) should be before the end date (yyyy/mm/dd).");
            }
            if (ldEnd.isBefore(LocalDate.now())) {
                end = Formatter.formatDatePrint(ldEnd);
                throw new InvalidInputException("The given event (end date: " + end + ") has ended.");
            }
            return new Event(name, ldStart, ldEnd);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException("Please enter the date/time in \"yyyy/mm/dd\" format.");
        }
    }

    /**
     * Parses the given string into the correct command.
     * @param input The string input given by the user.
     * @return Returns a Command representing the command given.
     * @throws DukeException if command is incomplete or invalid.
     */
    public static Command parse(String input) throws DukeException{
        String[] inputArr = input.split(" ", 2);
        CommandEnum commandType;
        try {
            commandType = CommandEnum.valueOf(inputArr[0].toUpperCase());
            switch (commandType) {
                case BYE:
                    return new ByeCommand();
                case LIST:
                    return new ListCommand();
                case MARK:
                    if (inputArr.length <= 1) {
                        throw new InvalidInputException("Please give the index of the item to be marked.");
                    }
                    return new MarkCommand(Integer.parseInt(inputArr[1]) - 1);
                case UNMARK:
                    if (inputArr.length <= 1) {
                        throw new InvalidInputException("Please give the index of the item to be unmarked.");
                    }
                    return new UnmarkCommand(Integer.parseInt(inputArr[1]) - 1);
                case DELETE:
                    if (inputArr.length <= 1) {
                        throw new InvalidInputException("Please give the index of the item to be deleted.");
                    }
                    return new DeleteCommand(Integer.parseInt(inputArr[1]) - 1);
                case TODO:
                    Task task = Parser.getTodo(inputArr);
                    return new AddCommand(task);
                case DEADLINE:
                    Deadline deadline = Parser.getDeadline(inputArr);
                    return new AddCommand(deadline);
                case EVENT:
                    Event event = Parser.getEvent(inputArr);
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
