package duke.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;
import duke.command.*;
import duke.task.*;

/**
 * Represents a Parser that parses in the user input.
 * Determines what Command it should execute based on the string format.
 */
public class Parser {

    /**
     * Parses a string input specifying a Date and Time.
     * @param dateTimeString string input specifying a Date and Time.
     * @return A LocalDateTime object of the specified Date and TIme.
     * @throws DukeException If the String input does not match the desired format of Date and Time.
     */
    public static LocalDateTime parseDateTime(String dateTimeString) throws DukeException {
        DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        try {
            return LocalDateTime.parse(dateTimeString, displayFormatter);
        } catch (DateTimeParseException err) {
            throw new DukeException("Invalid deadline format! Please format it in"
                                    + " <yyyy-MM-dd HHmm> to <yyyy-MM-dd HHmm>");
        }
    }

    /**
     * Parses a string input specifying a Date.
     * @param dateString String input specifying a Date.
     * @return A LocalDate object of the specified Date.
     * @throws DukeException if the String input does not match the desired format of Date.
     */
    public static LocalDate parseDate(String dateString) throws DukeException {
        try {
            return LocalDate.parse(dateString);
        } catch (DateTimeParseException err) {
            throw new DukeException("Invalid deadline format! Please format it in <yyyy-MM-dd>");
        }
    }

    /**
     * Parses the entire String input by the user and determines what command the input represents.
     * @param fullCommand String input specifying the type of command and its description.
     * @return A Command Object specifying what command the String input is addressing.
     * @throws DukeException If a task that the input is trying to add to the task list is invalid.
     */
    public static Command parse(String fullCommand) throws DukeException {
        Query.QueryType inputType = Query.getQueryType(fullCommand);
        String[] fullCommandArr = fullCommand.split(" ");

        switch (inputType) {
        case list:
            return new ListCommand();
        case todo:
            Todo todo = new Todo(fullCommand);
            return new AddCommand(todo);
        case deadline:
            Deadline deadline = new Deadline(fullCommand);
            return new AddCommand(deadline);
        case event:
            Event event = new Event(fullCommand);
            return new AddCommand(event);
        case find:
            return new FindCommand(fullCommand);
        case mark:
            return new MarkCommand(Integer.parseInt(fullCommandArr[1]));
        case unmark:
            return new UnmarkCommand(Integer.parseInt(fullCommandArr[1]));
        case delete:
            return new DeleteCommand(Integer.parseInt(fullCommandArr[1]));
        case exit:
            return new ExitCommand();
        default:
            return new InvalidCommand();
        }
    }
}
