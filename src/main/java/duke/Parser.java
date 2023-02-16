package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeException;
import duke.exception.IncompleteCommandException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidDateTimeException;
import duke.exception.InvalidTaskDurationException;
import duke.exception.InvalidTaskNumberException;
import duke.tag.Tag;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;


/**
 * A parser that deals with user input and transforms them into
 * a form that can be processed.
 */
public class Parser {
    public static final String DATE_TIME_READ_FORMAT = "yyyy-MM-dd HH:mm";

    Command processCommand(String command, TaskList tasks) throws DukeException {
        String[] commandArr = command.split("\\s+");
        try {
            switch (commandArr[0]) {
            case "list":
                return (commandArr.length == 1) ? new ListCommand(tasks) : new ListCommand(tasks, commandArr[1]);
            case "mark":
                return new MarkCommand(tasks, Integer.parseInt(commandArr[1]));
            case "unmark":
                return new UnmarkCommand(tasks, Integer.parseInt(commandArr[1]));
            case "delete":
                return new DeleteCommand(tasks, Integer.parseInt(commandArr[1]));
            case "find":
                return new FindCommand(tasks, command);
            case "todo":
                return new AddTodoCommand(tasks, command);
            case "deadline":
                return new AddDeadlineCommand(tasks, command);
            case "event":
                return new AddEventCommand(tasks, command);
            default:
                throw new InvalidCommandException();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IncompleteCommandException();
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException(commandArr[1]);
        }
    }

    /**
     * Parses a String representing a date and time and converts it to a
     * LocalDateTime object.
     *
     * @param dateTimeString String representing date and time to be parsed
     * @return LocalDateTime Object
     */
    public static LocalDateTime parseDateTime(String dateTimeString) throws InvalidDateTimeException {
        try {
            String[] dateTimeArray = dateTimeString.split(" ");
            LocalDate date = LocalDate.parse(dateTimeArray[0]);
            LocalTime time = LocalTime.parse(dateTimeArray[1]);
            return LocalDateTime.of(date, time);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidDateTimeException();
        }
    }

    /**
     * Parses user command to create a todo.
     *
     * @param contents Inputs to create a ToDo object.
     * @return A ToDo object.
     */
    public static ToDo parseToDo(String contents) throws IndexOutOfBoundsException {
        ToDo task;
        if (!contents.contains("/tag")) {
            String description = contents.substring(5);
            task = new ToDo(description);
        } else {
            int tagIndex = contents.indexOf("/tag");
            String description = contents.substring(5, tagIndex - 1);
            String tagName = contents.substring(tagIndex + 5).trim();
            Tag tag = new Tag(tagName.split(" ")[0]);
            task = new ToDo(description, tag);
        }
        return task;
    }


    /**
     * Parses user command to create a deadline.
     *
     * @param contents Inputs to create a Deadline object.
     * @return A Deadline object.
     */
    public static Deadline parseDeadline(String contents) throws DukeException, StringIndexOutOfBoundsException {
        Deadline task;
        int doneByIndex = contents.indexOf("/by");
        String description = contents.substring(9, doneByIndex - 1);
        if (!contents.contains("/tag")) {
            String doneByString = contents.substring(doneByIndex + 4);
            LocalDateTime doneBy = parseDateTime(doneByString);
            task = new Deadline(description, doneBy);
        } else {
            int tagIndex = contents.indexOf("/tag");
            String doneByString = contents.substring(doneByIndex + 4, tagIndex - 1);
            LocalDateTime doneBy = parseDateTime(doneByString);
            String tagName = contents.substring(tagIndex + 5).trim();
            Tag tag = new Tag(tagName.split(" ")[0]);
            task = new Deadline(description, doneBy, tag);
        }
        return task;
    }

    /**
     * Parses user command to create an event.
     *
     * @param contents Inputs to create an Event object.
     * @return An Event object.
     */
    public static Event parseEvent(String contents) throws DukeException, StringIndexOutOfBoundsException {
        int startIndex = contents.indexOf("/from");
        int endIndex = contents.indexOf("/to");
        String description = contents.substring(6, startIndex - 1);
        String startString = contents.substring(startIndex + 6, endIndex - 1);
        LocalDateTime start = Parser.parseDateTime(startString);
        Event task;
        if (!contents.contains("/tag")) {
            String endString = contents.substring(endIndex + 4);
            LocalDateTime end = Parser.parseDateTime(endString);
            checkValidStartEnd(start, end);
            task = new Event(description, start, end);
        } else {
            int tagIndex = contents.indexOf("/tag");
            String endString = contents.substring(endIndex + 4, tagIndex - 1);
            LocalDateTime end = Parser.parseDateTime(endString);
            checkValidStartEnd(start, end);
            String tagName = contents.substring(tagIndex + 5).trim();
            Tag tag = new Tag(tagName.split(" ")[0]);
            task = new Event(description, start, end, tag);
        }
        return task;
    }

    private static void checkValidStartEnd(LocalDateTime start, LocalDateTime end) throws DukeException {
        if (start.isAfter(end)) {
            throw new InvalidTaskDurationException();
        }
    }
}
