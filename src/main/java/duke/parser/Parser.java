package duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.Formatter;
import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.exception.IncompleteDescException;
import duke.exception.InvalidInputException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Encapsulates the related fields and behavior of a parser that parses the inputs given.
 */
public class Parser {
    private enum CommandEnum {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE
    }

    /**
     * Instantiates a new ToDo task and returns it.
     *
     * @param inputArr An array containing the input by the user.
     * @return A new ToDo task.
     * @throws IncompleteDescException If name of the task is not given.
     */
    private static ToDo getTodo(String[] inputArr) throws IncompleteDescException {
        if (inputArr.length <= 1 || inputArr[1].isBlank()) {
            throw new IncompleteDescException("The description of a todo cannot be empty!");
        }
        return new ToDo(inputArr[1]);
    }

    /**
     * Parses the given string into a LocalDate object.
     *
     * @param date The given string representation of the date to be parsed.
     * @return The parsed LocalDate.
     */
    public static LocalDate parseDate(String date) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return LocalDate.parse(date, dtf);
    }


    /**
     * Instantiates a new Deadline object and returns it.
     *
     * @param inputArr An array containing the input by the user.
     * @return A new Deadline object.
     * @throws IncompleteDescException If name or due date/time or both is/are not provided.
     * @throws InvalidInputException If due date/time is given in the wrong format.
     */
    private static Deadline getDeadline(String[] inputArr) throws IncompleteDescException, InvalidInputException {
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
            return new Deadline(name, ldEnd);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException("Please enter the date/time in \"yyyy/mm/dd\" format.");
        }
    }

    /**
     * Instantiate a new Event object and returns it.
     *
     * @param inputArr An array containing the input by the user.
     * @return A new Event.
     * @throws IncompleteDescException If the name, start date/time or due date/time are not given.
     * @throws InvalidInputException If any of the date/time are given
     *                               in a format different from "yyyy/MM/dd".
     */
    private static Event getEvent(String[] inputArr) throws IncompleteDescException, InvalidInputException {
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
     *
     * @param input The string input given by the user.
     * @return A Command representing the command given.
     * @throws IncompleteDescException If command given is incomplete.
     * @throws InvalidInputException If command is invalid.
     */
    public static Command parse(String input) throws IncompleteDescException, InvalidInputException {
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
