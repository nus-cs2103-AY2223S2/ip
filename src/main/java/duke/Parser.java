package duke;
import java.time.format.DateTimeParseException;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

public class Parser {

    private static final String END_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String TODO_COMMAND = "todo";
    private static final String EVENT_COMMAND = "event";
    private static final String DELETE_COMMAND = "delete";
    private static final String byIndicator = "/by";
    private static final String fromIndicator = "/from";
    private static final String toIndicator = "/to";

    public static Command parseCommand(String command) throws DukeException {
        if (command.equals(Parser.END_COMMAND)) {
            return new ExitCommand();
        } else if (command.equals(Parser.LIST_COMMAND)) {
            return new ListCommand();
        } else {
            return processCommand(command);
        }
    }

    private static Command processCommand(String line) throws DukeException {
        String[] splitCommand = line.split(" ");
        String command = splitCommand[0];
        if (command.equals(Parser.MARK_COMMAND) || command.equals(Parser.UNMARK_COMMAND)
            || command.equals(Parser.DELETE_COMMAND)) {
            return processTask(command, splitCommand);
        } else if (command.equals(Parser.TODO_COMMAND) || command.equals(Parser.DEADLINE_COMMAND)
            || command.equals(Parser.EVENT_COMMAND)) {
            return addTask(command, line);
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private static Command processTask(String command, String[] splitCommand) throws DukeException {
        if (splitCommand.length == 1) {
            throw new DukeException("A task number needs to be provided.");
        } else if (splitCommand.length > 2) {
            throw new DukeException("I don't recognise that task number.");
        }
        try {
            int taskIndex = Integer.parseInt(splitCommand[1]) - 1;
            if (command.equals(Parser.MARK_COMMAND)) {
                return new MarkCommand(taskIndex);
            } else if (command.equals(Parser.UNMARK_COMMAND)) {
                return new UnmarkCommand(taskIndex);
            } else {
                return new DeleteCommand(taskIndex);
            }
        } catch (NumberFormatException e) {
            throw new DukeException("I don't recognise that task number.");
        }

    }

    private static Command addTask(String command, String line) throws DukeException {
        Task task = null;
        switch (command) {
        case Parser.TODO_COMMAND:
            try {
                String description = line.split(Parser.TODO_COMMAND)[1].trim();
                if (description.isEmpty()) {
                    throw new DukeException("The description of a todo cannot be empty.");
                }
                task = new Todo(description);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            break;
        case Parser.DEADLINE_COMMAND:
            try {
                String details = line.split(Parser.DEADLINE_COMMAND)[1].trim();
                String name = details.split(Parser.byIndicator)[0].trim();
                String deadline = details.split(Parser.byIndicator)[1].trim();
                if (name.isEmpty() || deadline.isEmpty()) {
                    throw new DukeException("The description and /by of a deadline cannot be empty.");
                }
                task = new Deadline(name, deadline);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("The description and /by of a deadline cannot be empty.");
            } catch (DateTimeParseException e) {
                throw new DukeException("The inputted date(s) aren't formatted correctly!");
            }
            break;
        case Parser.EVENT_COMMAND:
            try {
                String details = line.split(Parser.EVENT_COMMAND)[1].trim();
                String name = details.split(Parser.fromIndicator)[0].trim();
                String from = details.split(Parser.fromIndicator)[1].split(Parser.toIndicator)[0].trim();
                String to = details.split(Parser.fromIndicator)[1].split(Parser.toIndicator)[1].trim();
                if (name.isEmpty() || from.isEmpty() || to.isEmpty()) {
                    throw new DukeException("The description, /from and /to of a deadline cannot be empty.");
                }
                task = new Event(name, from, to);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("The description, /from and /to of a deadline cannot be empty.");
            } catch (DateTimeParseException e) {
                throw new DukeException("The inputted date(s) aren't formatted correctly!");
            }
            break;
        default:
            break;
        }
        return new AddCommand(task);
    }
}
