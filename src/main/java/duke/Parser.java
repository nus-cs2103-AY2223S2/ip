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
import duke.command.ViewCommand;
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
        String commandType = commandDetails[0];

        trimArgs(commandDetails);
        checkSufficientArgs(commandType, commandDetails);
        String arguments = commandDetails.length < 2 ? "" : commandDetails[1];

        switch (commandType) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(parseIntArg(arguments));
        case "unmark":
            return new UnmarkCommand(parseIntArg(arguments));
        case "delete":
             return new DeleteCommand(parseIntArg(arguments));
        case "find":
            return new FindCommand(arguments);
        case "view":
            return new ViewCommand(parseDate(arguments));
        case "todo":
            Todo todo = new Todo(parseTodo(arguments));
            return new ToDoCommand(todo);
        case "deadline":
            String[] deadlineDetails = parseDeadline(arguments);
            return new DeadlineCommand(assignDeadline(deadlineDetails));
        case "event":
            String[] eventDetails = parseEvent(arguments);
            return new EventCommand(assignEvent(eventDetails));
        default:
            throw new DukeException("none");
        }
    }

    /**
     * Checks if there are sufficient arguments given.
     * @param commandType Specific command.
     * @param commandDetails Number of arguments given.
     * @throws DukeException if there are insufficient arguments given
     */
    public void checkSufficientArgs(String commandType, String[] commandDetails) throws DukeException {
        boolean isSingle = commandDetails.length < 2;
        boolean isSingleCommand = (commandType.equals("bye") || commandType.equals("list"));
        boolean hasTooManyArg = !isSingle && isSingleCommand;
        boolean hasInsufficientArgs = isSingle && !isSingleCommand;

        if (hasTooManyArg) {
            throw new DukeException("too many details");
        } else if (hasInsufficientArgs) {
            throw new DukeException(commandType);
        } else {}
    }

    /**
     * Trims whitespace from each argument.
     * @param arguments user's command inputs.
     */
    public void trimArgs(String[] arguments) {
        for (int i = 0; i < arguments.length; i++) {
            arguments[i] = arguments[i].trim();
            if (arguments[i].isEmpty()) {
                arguments[i] = null;
            } else {
                continue;
            }
        }
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
            return intArgument - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("input type");
        }
    }

    public LocalDate parseDate(String argument) throws DukeException {
        try {
            LocalDate date = LocalDate.parse(argument);
            return date;
        } catch (DateTimeParseException e) {
            throw new DukeException("date format");
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
     * Creates a Deadline from deadlineDetails.
     * @param deadlineDetails contains inputs for a Deadline instance.
     * @return a Deadline.
     */
    public Deadline assignDeadline(String[] deadlineDetails) {
        String taskName = deadlineDetails[0];
        LocalDate endDate = LocalDate.parse(deadlineDetails[1]);
        return new Deadline(taskName, endDate);
    }

    /**
     * Creates an Event from eventDetails.
     * @param eventDetails contains inputs for an Event instance.
     * @return an Event.
     */
    public Event assignEvent(String[] eventDetails) {
        String taskName = eventDetails[0];
        LocalDate startDate = LocalDate.parse(eventDetails[1]);
        LocalDate endDate = LocalDate.parse(eventDetails[2]);
        return new Event(taskName, startDate, endDate);
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
        } else if (deadlineDetails[0].isEmpty()) {
            throw new DukeException("deadline");
        }
        try {
            trimArgs(deadlineDetails);
            LocalDate end = LocalDate.parse(deadlineDetails[1]);
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
            throw new DukeException("event");
        }

        String[] eventDetails = argument.split("/from |/by ");

        if (eventDetails.length != 3) {
            throw new DukeException("timing");
        } else if (argument.indexOf("/from") > argument.indexOf("/by")) {
            throw new DukeException("wrong order");
        } else if (eventDetails[0].isEmpty()) {
            throw new DukeException("event");
        }
        try {
            trimArgs(eventDetails);
            LocalDate start = LocalDate.parse(eventDetails[1]);
            LocalDate end = LocalDate.parse(eventDetails[2]);
        } catch (DateTimeParseException e) {
            throw new DukeException("date format");
        }
        return eventDetails;
    }

}