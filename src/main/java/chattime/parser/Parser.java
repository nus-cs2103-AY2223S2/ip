package chattime.parser;

import chattime.command.*;
import chattime.exception.ChattimeException;
import chattime.task.Deadline;
import chattime.task.Event;
import chattime.task.Task;
import chattime.task.Todo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class Parser {

    private String userInput;
    private String[] splitCmd;
    private String command;
    private String description = null;
    private static final String NO_DESCRIPTION = "OOPS!!! The description of %s cannot be empty.";
    private static final String MISSED_PARAM = "OOPS!!! %s should be in form of %s.";
    private static final String NO_INDEX = "OOPS!!! The index to %1$s cannot be empty.";
    private static final String NEED_INT = "OOPS!!! The index to %1$s must be positive integer.";
    private static final String IDX_OUT_OF_BOUND = "OOPS!!! The index is too large! We currently have %d task(s).";
    private static final String UNRECOGNISED_COMMAND = "Sorry... but I don't understand what you said >,<";

    public Parser(String userInput) {
        this.userInput = userInput;
        splitCmd = userInput.split(" ", 2);
        command = splitCmd[0];
        if (splitCmd.length > 1) {
            description = splitCmd[1];
        }
    }

    public Command parse() throws ChattimeException {
        checkCleanCommand();
        switch (command) {
        case "bye":
            return parseBye();
        case "list":
            return parseList();
        case "todo":
        case "deadline":
        case "event":
        case "listTime":
            checkAddCommand();
            switch (command) {
            case "todo":
                return parseTodo();
            case "deadline":
                return parseDeadline();
            case "event":
                return parseEvent();
            case "listTime":
                return parseListTime();
            }
        case "mark":
        case "unmark":
        case "delete":
            int index = checkIndexCommand();
            if (command.equals("mark")) {
                return new MarkCommand(index, true);
            } else if (command.equals("unmark")) {
                return new MarkCommand(index, false);
            } else {
                return new DeleteCommand(index);
            }
        default:
            throw new ChattimeException(UNRECOGNISED_COMMAND);
        }
    }

    public void checkCleanCommand() throws ChattimeException {
        if (userInput.contains("@")) {
            throw new ChattimeException("@^@ I'm sorry but your message should not contain any \"@\" .");
        }
    }

    public void checkAddCommand() throws ChattimeException {
        if (description == null) {
            throw new ChattimeException(String.format(NO_DESCRIPTION, command));
        }
    }

    public int checkIndexCommand() throws ChattimeException {
        if (description == null) {
            throw new ChattimeException(String.format(NO_INDEX, command));
        } else if (Pattern.matches("^[0-9]*$", description)) {
            int index = Integer.parseInt(description);
            if (index > Task.getCount() || index < 1) {
                throw new ChattimeException(String.format(IDX_OUT_OF_BOUND, Task.getCount()));
            }
            return index;
        } else {
            throw new ChattimeException(String.format(NEED_INT, command));
        }
    }

    public ListCommand parseList() throws ChattimeException {
        if (description != null) {
            throw new ChattimeException("OOPS!!! list does not take any description.");
        }
        return new ListCommand(null);
    }

    public ByeCommand parseBye() throws ChattimeException {
        if (description != null) {
            throw new ChattimeException("Type \"bye\" if you really want to say goodbye to me.");
        }
        return new ByeCommand();
    }

    public AddCommand parseTodo() {
        Todo todo = new Todo(description);
        return new AddCommand(todo);
    }

    public AddCommand parseDeadline() throws ChattimeException {
        String[] splitBy = description.split(" /by ", 2);
        if (splitBy.length < 2 || splitBy[1].equals("")) {
            throw new ChattimeException(
                    String.format(MISSED_PARAM, command, "deadline (task) /by (yyyy-mm-dd hh:mm)"));
        }
        String task = splitBy[0];
        Deadline deadlineTask;
        try {
            String[] time = splitBy[1].split(" ", 2);
            LocalDate byDate = LocalDate.parse(time[0]);
            if (time.length == 1) {
                deadlineTask = new Deadline(task, byDate, null);
            } else {
                LocalTime byTime = LocalTime.parse(time[1]);
                deadlineTask = new Deadline(task, byDate, byTime);
            }
            return new AddCommand(deadlineTask);
        } catch (DateTimeParseException e) {
            throw new ChattimeException("OOPS!!! Please enter date and time in format yyyy-mm-dd or yyyy-mm-dd hh:mm");
        }
    }

    public AddCommand parseEvent() throws ChattimeException {
        String[] splitTask = description.split(" /from ", 2);
        String task = splitTask[0];

        if (splitTask.length < 2 || splitTask[1].equals("")) {
            throw new ChattimeException(String.format(
                    MISSED_PARAM, command, "event (task) /from (yyyy-mm-dd hh:mm) /to (yyyy-mm-dd hh:mm)"));
        }
        String[] splitFrom = splitTask[1].split(" /to ", 2);

        if (splitFrom.length < 2 || splitFrom[1].equals("")) {
            throw new ChattimeException(String.format(
                    MISSED_PARAM, command, "event (task) /from (yyyy-mm-dd hh:mm) /to (yyyy-mm-dd hh:mm)"));
        }
        try {
            String[] from = splitFrom[0].split(" ", 2);
            String[] to = splitFrom[1].split(" ", 2);
            LocalDate fromDate = LocalDate.parse(from[0]);
            LocalTime fromTime = LocalTime.parse(from[1]);
            LocalDate toDate = LocalDate.parse(to[0]);
            LocalTime toTime = LocalTime.parse(to[1]);

            Event eventTask = new Event(task, fromDate, fromTime, toDate, toTime);
            return new AddCommand(eventTask);
        } catch (DateTimeParseException e) {
            throw new ChattimeException("OOPS!!! Please enter both date and time in format yyyy-mm-dd hh:mm");
        }
    }

    public ListCommand parseListTime() throws ChattimeException {
        if (description == null) {
            throw new ChattimeException(
                    String.format(MISSED_PARAM, command, "listTime yyyy-mm-dd"));
        }
        try {
            LocalDate date = LocalDate.parse(description);
            return new ListCommand(date);
        } catch (DateTimeParseException e) {
            throw new ChattimeException("OOPS!!! Please enter date and time in format yyyy-mm-dd");
        }
    }

}
