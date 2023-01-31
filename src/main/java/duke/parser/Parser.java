package duke.parser;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;

import duke.exception.DukeException;
import duke.exception.DukeInvalidArgumentException;
import duke.exception.DukeInvalidCommandException;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.Arrays;
import java.util.List;

/**
 * Represents a parser that parses the user input into an executable command.
 */
public class Parser {

    private static LocalDateTime parseDateTime(String dateTimeText) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        LocalDateTime dateTime;

        try {
            dateTime = LocalDateTime.parse(dateTimeText, dateTimeFormatter);

        } catch (DateTimeParseException e) {
            dateTime = null;

        }

        return dateTime;

    }

    private static Command parseList(String[] commandParts) throws DukeException {
        if (commandParts.length == 1) {
            return new ListCommand();

        } else {
            throw new DukeInvalidCommandException("Sorry. That is an invalid command :/");
        }

    }

    private static Command parseDelete(String[] commandParts) throws DukeException {

        if (commandParts.length != 2) {
            throw new DukeInvalidCommandException("Sorry... That is an invalid command :/");
        }

        try {
            int taskNumber = Integer.parseInt(commandParts[1]);
            return new DeleteCommand(taskNumber);

        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentException("Sorry... That is an invalid task number :/");
        }

    }

    private static Command parseMarkUnmark(String commandHeader, String[] commandParts) throws DukeException {

        if (commandParts.length != 2) {
            throw new DukeInvalidCommandException("Sorry... That is an invalid command :/");
        }

        try {
            int taskNumber = Integer.parseInt(commandParts[1]);

            if (commandHeader.equals("mark")) {
                return new MarkCommand(taskNumber);
            } else {
                return new UnmarkCommand(taskNumber);
            }

        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentException("Sorry... That is an invalid task number :/");
        }

    }

    private static Command parseToDo(String[] commandParts) throws DukeException {
        if (commandParts.length == 1) {
            throw new DukeInvalidArgumentException("Uh-oh. The description of the to-do is empty :/");
        }

        String[] descWords = Arrays.copyOfRange(commandParts, 1, commandParts.length);
        String description = String.join(" ", descWords);

        Task toDo = new ToDo(description);

        return new AddCommand(toDo, "to-do");

    }

    private static Command parseDeadline(String[] commandParts) throws DukeException {
        if (commandParts.length == 1) {
            throw new DukeInvalidCommandException("Uh-oh. There is no about the deadline :/");
        }

        String[] commandRest = Arrays.copyOfRange(commandParts, 1, commandParts.length);
        List<String> descList = Arrays.asList(commandRest);

        int byIndex = descList.indexOf("/by");
        if (byIndex == -1) {
            throw new DukeInvalidCommandException("Uh-oh. There is no 'by' given for the deadline :/");
        }

        if (byIndex == 0) {
            throw new DukeInvalidArgumentException("Uh-oh. The description of the deadline is empty :/");
        }

        if (commandRest.length - 1 == byIndex) {
            throw new DukeInvalidArgumentException("Uh-oh. The 'by' of the deadline is empty :/");
        }

        String[] descWords = Arrays.copyOfRange(commandRest, 0, byIndex);
        String description = String.join(" ", descWords);

        String[] byArray = Arrays.copyOfRange(commandRest, byIndex + 1, commandRest.length);
        String by = String.join(" ", byArray);

        LocalDateTime byDateTime = Parser.parseDateTime(by);

        if (byDateTime == null) {
            throw new DukeInvalidArgumentException(
                    "Oops! Invalid date-time format. It should be DD-MM-YYYY hhmm (24-hrs format)");
        }

        Task deadline = new Deadline(description, byDateTime);
        return new AddCommand(deadline, "deadline");

    }

    private static Command parseEvent(String[] commandParts) throws DukeException {
        if (commandParts.length == 1) {
            throw new DukeInvalidCommandException("Uh-oh. There is no information about the event :/");
        }

        String[] commandRest = Arrays.copyOfRange(commandParts, 1, commandParts.length);
        List<String> descList = Arrays.asList(commandRest);

        int fromIndex = descList.indexOf("/from");
        if (fromIndex == -1) {
            throw new DukeInvalidCommandException("Uh-oh. There is no 'from' given for the event :/");
        }

        int toIndex = descList.indexOf("/to");
        if (toIndex == -1) {
            throw new DukeInvalidCommandException("Uh-oh. There is no 'to' given for the event :/");
        }

        if (Math.min(fromIndex, toIndex) == 0) {
            throw new DukeInvalidArgumentException("Uh-oh. The description of the event is empty :/");
        }

        if (toIndex - fromIndex == 1 || commandRest.length - 1 == fromIndex) {
            throw new DukeInvalidArgumentException("Uh-oh. The 'from' of the deadline is empty :/");
        }

        if (fromIndex - toIndex == 1 || commandRest.length - 1 == toIndex) {
            throw new DukeInvalidArgumentException("Uh-oh. The 'to' of the event is empty :/");
        }

        String[] descWords =
                Arrays.copyOfRange(commandRest, 0, Math.min(fromIndex, toIndex));
        String description = String.join(" ", descWords);

        String[] fromArray;
        String[] toArray;

        if (fromIndex > toIndex) {
            fromArray =
                    Arrays.copyOfRange(commandRest, fromIndex + 1, commandRest.length);
            toArray =
                    Arrays.copyOfRange(commandRest, toIndex + 1, fromIndex);

        } else {
            fromArray =
                    Arrays.copyOfRange(commandRest, fromIndex + 1, toIndex);
            toArray =
                    Arrays.copyOfRange(commandRest, toIndex + 1, commandRest.length);
        }

        String from = String.join(" ", fromArray);
        String to = String.join(" ", toArray);

        LocalDateTime fromDateTime = Parser.parseDateTime(from);
        LocalDateTime toDateTime = Parser.parseDateTime(to);

        if (fromDateTime == null || toDateTime == null) {
            throw new DukeInvalidArgumentException(
                    "Oops! Invalid date-time format. It should be DD-MM-YYYY hhmm (24-hrs format)");
        }

        if (toDateTime.isBefore(fromDateTime)) {
            throw new DukeInvalidArgumentException(
                    "Uh-oh, those are incompatible dates and times for 'from' and 'to' :/");
        }

        Task event = new Event(description, fromDateTime, toDateTime);
        return new AddCommand(event, "event");

    }

    /**
     * Parses the user's text input into an executable command.
     *
     * @param userCommand The user's text input.
     * @return The executable command obtained from the user's input.
     * @throws DukeException When the user's input cannot be parsed into an executable command.
     */
    public static Command parse(String userCommand) throws DukeException {

        String command = userCommand.strip().replaceAll("( )+", " ");

        if (command.equals("")) {
            throw new DukeInvalidCommandException("You have not entered anything...");
        }

        String[] commandParts = command.split(" ");
        String commandHeader = commandParts[0].toLowerCase();
        
        if (commandHeader.equals("bye")) {
            return new ExitCommand();
            
        } else if (commandHeader.equals("list")) {
            return parseList(commandParts);

        } else if (commandHeader.equals("delete")) {
            return parseDelete(commandParts);

        } else if (commandHeader.equals("mark") || commandHeader.equals("unmark")) {
            return parseMarkUnmark(commandHeader, commandParts);

        } else if (commandHeader.equals("todo")) {
            return parseToDo(commandParts);

        } else if (commandHeader.equals("deadline")) {
            return parseDeadline(commandParts);

        } else if (commandHeader.equals("event")) {
            return parseEvent(commandParts);

        } else {
            throw new DukeInvalidCommandException("Sorry... I did not understand that :/");

        }

    }

}