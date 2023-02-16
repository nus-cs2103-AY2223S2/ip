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
import duke.exception.InvalidCommandException;
import duke.exception.InvalidDateTimeException;


/**
 * A parser that deals with user input and transforms them into
 * a form that can be processed.
 */
public class Parser {
    public static final String DATE_TIME_READ_FORMAT = "yyyy-MM-dd HH:mm";

    Command processCommand(String command, TaskList tasks) throws DukeException {
        String[] commandArr = command.split(" ");
        switch (commandArr[0]) {
        case "list":
            return (commandArr.length == 1)  ? new ListCommand(tasks) : new ListCommand(tasks, commandArr[1]);
        case "mark":
            return new MarkCommand(tasks, Integer.parseInt(commandArr[1]));
        case "unmark":
            return new UnmarkCommand(tasks, Integer.parseInt(commandArr[1]));
        case "delete":
            return new DeleteCommand(tasks, Integer.parseInt(commandArr[1]));
        case "find":
            return new FindCommand(tasks, commandArr[1]);
        case "todo":
            return new AddTodoCommand(tasks, command);
        case "deadline":
            return new AddDeadlineCommand(tasks, command);
        case "event":
            return new AddEventCommand(tasks, command);
        default:
            throw new InvalidCommandException();
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
        } catch (ArrayIndexOutOfBoundsException e){
            throw new InvalidDateTimeException();
        }
    }

}
