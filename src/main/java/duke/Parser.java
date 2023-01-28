package duke;

import duke.command.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {
    public Parser() {}

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

    public int parseIntArg(String argument) throws DukeException {
        int intArgument;
        try {
            intArgument = Integer.parseInt(argument.trim());
            return intArgument;
        } catch (NumberFormatException e) {
            throw new DukeException("input type");
        }
    }

    public String parseTodo(String argument) throws DukeException {
        String taskName = argument.trim();
        if (taskName.isEmpty()) {
            throw new DukeException("todo");
        } else {
            return taskName;
        }
    }

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
            LocalDate end = LocalDate.parse(deadlineDetails[1]);
        } catch (DateTimeParseException e) {
            throw new DukeException("date format");
        }
        return deadlineDetails;
    }

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
            LocalDate start = LocalDate.parse(eventDetails[1]);
            LocalDate end = LocalDate.parse(eventDetails[2]);
        } catch (DateTimeParseException e) {
            throw new DukeException("date format");
        }
        return eventDetails;
    }
}