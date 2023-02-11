package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.ToDoCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/** Class parses user input into commands. */
public class Parser {

    /** Empty constructor creates an instance of Parser. */
    public Parser() {}

    /**
     * Returns an appropriate command based on user input.
     * @param userInput String containing user input.
     * @return Command object based on user input.
     * @throws DukeException If user provides an inappropriate command format.
     */
    public Command parse(String userInput) throws DukeException {
        String[] commandDetails = userInput.trim().split(" ", 2);
        boolean single = commandDetails.length < 2;
        String commandType = commandDetails[0];
        String arguments = single ? "" : commandDetails[1];
        int index;
        String taskName;
        LocalDate startDate;
        LocalDate endDate;
        Command command = null;

        switch (commandType) {
        case "bye":
            if (!single) {
                throw new DukeException("too many details");
            }
            command = new ByeCommand();
            break;
        case "list":
            if (!single) {
                throw new DukeException("too many details");
            }
            command = new ListCommand();
            break;
        case "mark":
            if (single) {
                throw new DukeException(commandType);
            }
            index = parseIntArg(arguments);
            command = new MarkCommand(index - 1);
            break;
        case "unmark":
            if (single) {
                throw new DukeException(commandType);
            }
            index = parseIntArg(arguments);
            command = new UnmarkCommand(index - 1);
            break;
        case "delete":
            if (single) {
                throw new DukeException(commandType);
            }
            index = parseIntArg(arguments);
            command = new DeleteCommand(index - 1);
            break;
        case "find":
            if (single) {
                throw new DukeException("empty keyword");
            }
            command = new FindCommand(arguments);
            break;
        case "todo":
            if (single) {
                throw new DukeException(commandType);
            }
            Todo todo = new Todo(parseTodo(arguments));
            command = new ToDoCommand(todo);
            break;
        case "deadline":
            if (single) {
                throw new DukeException(commandType);
            }
            String[] deadlineDetails = parseDeadline(arguments);
            taskName = deadlineDetails[0];
            endDate = LocalDate.parse(deadlineDetails[1]);
            Deadline deadline = new Deadline(taskName, endDate);
            command = new DeadlineCommand(deadline);
            break;
        case "event":
            if (single) {
                throw new DukeException(commandType);
            }
            String[] eventDetails = parseEvent(arguments);
            taskName = eventDetails[0];
            startDate = LocalDate.parse(eventDetails[1]);
            endDate = LocalDate.parse(eventDetails[2]);
            Event event = new Event(taskName, startDate, endDate);
            command = new EventCommand(event);
            break;
        default:
            throw new DukeException("none");
        }
        return command;
    }

    /**
     * Returns an integer from parsed string argument if a valid argument is given.
     * @param argument Parsed string argument that should contain an integer.
     * @return Parsed integer.
     * @throws DukeException If argument given is empty or not an integer.
     */
    public int parseIntArg(String argument) throws DukeException {
        int intArgument;
        try {
            intArgument = Integer.parseInt(argument.trim());
            return intArgument;
        } catch (NumberFormatException e) {
            throw new DukeException("input type");
        }
    }

    /**
     * Returns the taskname for a Todotask from the given argument.
     * @param argument String containing user input for a Todotask command.
     * @return taskName for a Todotask.
     * @throws DukeException If argument is empty.
     */
    public String parseTodo(String argument) throws DukeException {
        String taskName = argument.trim();
        if (taskName.isEmpty()) {
            throw new DukeException("todo");
        } else {
            return taskName;
        }
    }

    /**
     * Returns parsed inputs for creating a Deadline from the given user input.
     * @param argument String contains user input for creating a Deadline.
     * @return An array of strings containing the taskName and deadline for creating a Deadline.
     * @throws DukeException If any of the user inputs are empty or of the wrong format.
     */
    public String[] parseDeadline(String argument) throws DukeException {
        argument = argument.trim();
        if (argument.isEmpty()) {
            throw new DukeException("deadline");
        }
        String[] deadlineDetails = argument.split("/by ");
        if (deadlineDetails.length != 2) {
            throw new DukeException("timing");
        }
        try {
            deadlineDetails[0] = deadlineDetails[0].trim();
            LocalDate end = LocalDate.parse(deadlineDetails[1].trim());
            deadlineDetails[1] = deadlineDetails[1].trim();
        } catch (DateTimeParseException e) {
            throw new DukeException("date format");
        }
        return deadlineDetails;
    }

    /**
     * Returns parsed inputs for creating an Event from the given user input.
     * @param argument String contains user input for creating an Event.
     * @return An array of strings containing the taskName, startDate and endDate for creating an Event.
     * @throws DukeException If any of the user inputs are empty or of the wrong format.
     */
    public String[] parseEvent(String argument) throws DukeException {
        argument = argument.trim();
        if (argument.isEmpty()) {
            throw new DukeException("deadline");
        }
        String[] eventDetails = argument.split("/from |/by ");
        if (eventDetails.length != 3) {
            throw new DukeException("timing");
        } else if (argument.indexOf("/from") > argument.indexOf("/by")) {
            throw new DukeException("wrong order");
        }
        try {
            eventDetails[0] = eventDetails[0].trim();
            LocalDate start = LocalDate.parse(eventDetails[1].trim());
            LocalDate end = LocalDate.parse(eventDetails[2].trim());
            eventDetails[1] = eventDetails[1].trim();
            eventDetails[2] = eventDetails[2].trim();
        } catch (DateTimeParseException e) {
            throw new DukeException("date format");
        }
        return eventDetails;
    }
}