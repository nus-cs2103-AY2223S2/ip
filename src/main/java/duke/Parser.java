package duke;

import duke.command.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {


    public static LocalDateTime parseDateTime (String dateTimeString) throws DukeException {
        DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        try {
            return LocalDateTime.parse(dateTimeString, displayFormatter);
        } catch (DateTimeParseException err) {
            throw new DukeException("Invalid deadline format! Please format it in <yyyy-MM-dd HHmm> to <yyyy-MM-dd HHmm>");
        }
    }

    public static LocalDate parseDate (String dateString) throws DukeException {
        try {
            return LocalDate.parse(dateString);
        } catch (DateTimeParseException err) {
            throw new DukeException("Invalid deadline format! Please format it in <yyyy-MM-dd>");
        }
    }
    public static Command parse(String fullCommand) throws DukeException {
        QueryType inputType = Query.queryType(fullCommand);
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