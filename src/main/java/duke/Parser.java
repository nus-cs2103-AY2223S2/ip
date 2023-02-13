package duke;

import duke.command.Command;
import duke.command.CreateDeadline;
import duke.command.CreateEvent;
import duke.command.CreateTodo;
import duke.command.Remove;
import duke.command.Exit;
import duke.command.Find;
import duke.command.List;
import duke.command.Mark;
import duke.command.Set;
import duke.command.Unmark;
import duke.exceptions.DukeException;
import duke.exceptions.InsufficientArgumentsException;
import duke.exceptions.InvalidArgumentException;
import duke.exceptions.InvalidCommandException;

import java.time.LocalDate;

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
        String command = split[0];
        boolean hasOneArg = split.length == 1;

        if (Exit.checkAlias(command) && hasOneArg) {
            return new Exit();
        } else if (List.checkAlias(command) && hasOneArg) {
            return new List();
        } else if (Mark.checkAlias(command)) {
            if (hasOneArg) {
                handleInsufficientArgs("mark");
            }
            try {
                Integer i = Integer.parseInt(split[1]);
                return new Mark(i);
            } catch (NumberFormatException e) {
                throw new InvalidArgumentException(split[1], "the task number");
            }
        } else if (Unmark.checkAlias(command)) {
            if (hasOneArg) {
                handleInsufficientArgs("unmark");
            }
            try {
                Integer i = Integer.parseInt(split[1]);
                return new Unmark(i);
            } catch (NumberFormatException e) {
                throw new InvalidArgumentException(split[1], "the task number");
            }
        } else if (Remove.checkAlias(command)) {
            if (hasOneArg) {
                handleInsufficientArgs("remove");
            }
            try {
                Integer i = Integer.parseInt(split[1]);
                return new Remove(i);
            } catch (NumberFormatException e) {
                throw new InvalidArgumentException(split[1], "the task number");
            }
        } else if (Find.checkAlias(command)) {
            if (hasOneArg) {
                handleInsufficientArgs("find");
            }
            return new Find(split[1]);
        } else if (CreateTodo.checkAlias(command)) {
            if (hasOneArg) {
                handleInsufficientArgs("todo");
            }
            return new CreateTodo(split[1]);
        } else if (CreateDeadline.checkAlias(command)) {
            if (hasOneArg) {
                handleInsufficientArgs("deadline");
            }
            String[] tokens = split[1].split(" /by ", 2);
            if (tokens.length == 1) {
                handleInsufficientArgs("deadline1");
            }
            LocalDate by = parseDate(tokens[1]);
            return new CreateDeadline(tokens[0], by);
        } else if (CreateEvent.checkAlias(command)) {
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
            LocalDate from = parseDate(tokens2[0]);
            LocalDate to = parseDate(tokens2[1]);
            return new CreateEvent(tokens[0], from, to);
        } else if (Set.checkAlias(command)) {
            if (hasOneArg) {
                handleInsufficientArgs("set");
            }
            String[] tokens = split[1].split(" ", 2);
            if (tokens.length == 1) {
                handleInsufficientArgs("set1");
            }
            return new Set(tokens[0], tokens[1]);
        } else {
            throw new InvalidCommandException();
        }
    }

    /**
     * Parses a date string into a LocalDate object.
     * 
     * @param date the date string to parse.
     * @return the LocalDate object.
     * @throws DukeException 
     */
    public static LocalDate parseDate(String date) throws InvalidArgumentException {
        try {
            return LocalDate.parse(date);
        } catch (Exception e) {
            throw new InvalidArgumentException(date, "a valid date in the format YYYY-MM-DD.");
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
            throw new InsufficientArgumentsException("description", "add");
        case "deadline":
            throw new InsufficientArgumentsException("description", "add");
        case "deadline1":
            throw new InsufficientArgumentsException("deadline");
        case "event":
            throw new InsufficientArgumentsException("description", "add");
        case "event1":
            throw new InsufficientArgumentsException("start time");
        case "event2":
            throw new InsufficientArgumentsException("end time");
        case "mark":
            throw new InsufficientArgumentsException("task", "mark as done");
        case "unmark":
            throw new InsufficientArgumentsException("task", "mark as not done");
        case "remove":
            throw new InsufficientArgumentsException("task", "remove");
        case "find":
            throw new InsufficientArgumentsException("keyword", "search for");
        case "set":
            throw new InsufficientArgumentsException("command", "add an alias for");
        case "set1":
            throw new InsufficientArgumentsException("alias");
        default:
            throw new InvalidCommandException();
        }
    }

}
