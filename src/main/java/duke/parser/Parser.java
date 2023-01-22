package duke.parser;

import java.time.format.DateTimeParseException;

import duke.DukeException;
import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddToDoCommand;
import duke.command.Command;
import duke.command.FindTasksCommand;
import duke.command.ListTasksCommand;
import duke.command.MarkTaskCommand;
import duke.command.RemoveTaskCommand;
import duke.command.UnmarkTaskCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

public class Parser {

    public Action parseAction(String actionString) {
        return Action.getAction(actionString.split(" ")[0]);
    }

    private String parseTask(String taskString) {
        return taskString.split(" ", 2)[1];
    }

    private String parseDescription(String taskString) {
        return parseTask(taskString).split(" /by ")[0];
    }

    private int parseIndex(String taskString) throws DukeException {
        try {
            return Integer.parseInt(taskString.split(" ")[1]) - 1;
        } catch (Exception e) {
            throw new DukeException("Please enter a valid task number.");
        }
    }

    private String parseDate(String taskString) {
        return parseTask(taskString).split(" /by ")[1];
    }

    private ToDo parseToDoTask(String taskString) throws DukeException {
        if (taskString.split(" ").length == 1) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        String description = parseTask(taskString);
        return new ToDo(description);
    }

    private Deadline parseDeadlineTask(String taskString) throws DukeException {
        if (taskString.split(" ").length == 1) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }
        if (!taskString.contains(" /by ")) {
            throw new DukeException("Please use the format: deadline <description> /by <time>");
        }
        String description = parseDescription(taskString);
        String by = parseDate(taskString);
        try {
            return new Deadline(description, by);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please use time format: d/MM/yyyy HHmm");
        }
    }

    private Event parseEventTask(String taskString) throws DukeException {
        if (taskString.split(" ").length == 1) {
            throw new DukeException("The description of an event cannot be empty.");
        }
        if (!taskString.contains(" /from ") || !taskString.contains(" /to ")) {
            throw new DukeException("Please use the format: event <description> /from <time> /to <time>");
        }
        String description = parseDescription(taskString);
        String[] splitTime = parseTask(taskString).split(" /from ")[1].split(" /to ");
        if (splitTime.length != 2) {
            throw new DukeException("Please use the format: event <description> /from <time> /to <time>");
        }
        String from = splitTime[0];
        String to = splitTime[1];
        try {
            return new Event(description, from, to);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please use time format: d/MM/yyyy HHmm");
        }
    }

    public Command parseCommand(String input) throws DukeException {
        Action action = parseAction(input);
        switch (action) {
            case LIST:
                return new ListTasksCommand();
            case MARK:
                return new MarkTaskCommand(parseIndex(input));
            case UNMARK:
                return new UnmarkTaskCommand(parseIndex(input));
            case DELETE:
                return new RemoveTaskCommand(parseIndex(input));
            case TODO:
                return new AddToDoCommand(parseToDoTask(input));
            case DEADLINE:
                return new AddDeadlineCommand(parseDeadlineTask(input));
            case EVENT:
                return new AddEventCommand(parseEventTask(input));
            case FIND:
                return new FindTasksCommand(parseTask(input));
            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

}
