package duke;

import duke.command.Command;
import duke.command.CreateDeadline;
import duke.command.CreateEvent;
import duke.command.CreateTodo;
import duke.command.Delete;
import duke.command.Exit;
import duke.command.Find;
import duke.command.List;
import duke.command.Mark;
import duke.command.Unmark;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Parses user input.
 */
public class Parser {
    /**
     * Parses a command string into a Command object.
     * 
     * @param fullCommand the command string to parse.
     * @return the Command object.
     * @throws DukeException
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] split = fullCommand.split(" ", 2);
        boolean hasOneArg = split.length == 1;

        if (split[0].equals("bye") && hasOneArg) {
            return new Exit();
        } else if (split[0].equals("list") && hasOneArg) {
            return new List();
        } else if (split[0].equals("mark")) {
            if (hasOneArg) {
                handleInsufficientArgs("mark");
            }
            try {
                Integer i = Integer.parseInt(split[1]);
                return new Mark(i);
            } catch (NumberFormatException e) {
                handleWrongTaskNumber();
            }
        } else if (split[0].equals("unmark")) {
            if (hasOneArg) {
                handleInsufficientArgs("unmark");
            }
            try {
                Integer i = Integer.parseInt(split[1]);
                return new Unmark(i);
            } catch (NumberFormatException e) {
                handleWrongTaskNumber();
            }
        } else if (split[0].equals("delete")) {
            if (hasOneArg) {
                handleInsufficientArgs("delete");
            }
            try {
                Integer i = Integer.parseInt(split[1]);
                return new Delete(i);
            } catch (NumberFormatException e) {
                handleWrongTaskNumber();
            }
        } else if (split[0].equals("find")) {
            if (hasOneArg) {
                handleInsufficientArgs("find");
            }
            return new Find(split[1]);
        } else if (split[0].equals("todo")) {
            if (hasOneArg) {
                handleInsufficientArgs("todo");
            }
            return new CreateTodo(split[1]);
        } else if (split[0].equals("deadline")) {
            if (hasOneArg) {
                handleInsufficientArgs("deadline");
            }
            String[] tokens = split[1].split(" /by ", 2);
            if (tokens.length == 1) {
                handleInsufficientArgs("deadline1");
            }
            return new CreateDeadline(tokens[0], parseDate(tokens[1]));
        } else if (split[0].equals("event")) {
            if (hasOneArg) {
                handleInsufficientArgs("event");
            }
            String[] tokens = split[1].split(" /from ", 2);
            if (tokens.length == 1) {
                handleInsufficientArgs("event1");
            }
            String[] tokens2 = tokens[1].split(" /to ", 2);
            if (tokens2.length == 1) {
                handleInsufficientArgs("event2");
            }
            return new CreateEvent(tokens[0], parseDate(tokens2[0]), parseDate(tokens2[1]));
        } else {
            handleUnknownCommand();
        }
        return null;
    }

    /**
     * Parses a date string into a LocalDate object.
     * 
     * @param date the date string to parse.
     * @return the LocalDate object.
     * @throws DukeException 
     */
    public static LocalDate parseDate(String date) throws DukeException {
        try {
            return LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please provide a valid date in the format yyyy-mm-dd.");
        }
    }

    /**
     * Handles insufficient arguments for a command.
     * 
     * @param command the command string.
     * @throws DukeException
     */
    public static void handleInsufficientArgs(String command) throws DukeException {
        switch (command) {
        case "todo":
            throw new DukeException("The description of a todo cannot be empty.");
        case "deadline":
            throw new DukeException("The description of a deadline cannot be empty.");
        case "deadline1":
            throw new DukeException("Please provide a deadline for this task.");
        case "event":
            throw new DukeException("The description of an event cannot be empty.");
        case "event1":
            throw new DukeException("Please provide a start time for this event.");
        case "event2":
            throw new DukeException("Please provide an end time for this event.");
        case "mark":
            throw new DukeException("Please specify the task you want to mark.");
        case "unmark":
            throw new DukeException("Please specify the task you want to unmark.");
        case "delete":
            throw new DukeException("Please specify the task you want to delete.");
        case "find":
            throw new DukeException("Please specify the keyword you want to find.");
        default:
            handleUnknownCommand();
        }
    }

    /**
     * Handles wrong task number.
     * @throws DukeException
     */
    public static void handleWrongTaskNumber() throws DukeException {
        throw new DukeException("Please specify a valid task number.");
    }

    /**
     * Handles any unknown command.
     * 
     * @throws DukeException
     */
    public static void handleUnknownCommand() throws DukeException {
        throw new DukeException("I'm sorry, but I don't know what that means :-(");
    }
}
