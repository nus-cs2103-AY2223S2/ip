package chattime.parser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

import chattime.command.AddCommand;
import chattime.command.ByeCommand;
import chattime.command.Command;
import chattime.command.DeleteCommand;
import chattime.command.FindCommand;
import chattime.command.ListCommand;
import chattime.command.MarkCommand;
import chattime.exception.ChattimeException;
import chattime.task.Deadline;
import chattime.task.Event;
import chattime.task.Task;
import chattime.task.Todo;


/**
 * Represents parser for bot, processes raw user input and creates appropriate Command object.
 */
public class Parser {

    private static final String NO_DESCRIPTION = "OOPS!!! The description of %s cannot be empty.";
    private static final String MISSED_PARAM = "OOPS!!! %s should be in form of %s.";
    private static final String NO_INDEX = "OOPS!!! The index to %1$s cannot be empty.";
    private static final String NEED_INT = "OOPS!!! The index to %1$s must be positive integer.";
    private static final String IDX_OUT_OF_BOUND = "OOPS!!! The index is too large! We currently have %d task(s).";
    private static final String UNRECOGNISED_COMMAND = "Sorry... but I don't understand what you said >,<";

    private String userInput;
    private String[] splitCommand;
    private String command;
    private String description = null;

    /**
     * Creates Parser object for user input.
     *
     * @param input Command entered by user.
     */
    public Parser(String input) {
        userInput = input;
        splitCommand = userInput.split(" ", 2);
        command = splitCommand[0];

        if (splitCommand.length > 1) {
            description = splitCommand[1];
        }
    }

    /**
     * Returns suitable Command object for further execution.
     *
     * @return Command object to execute user request.
     * @throws ChattimeException Exception thrown when command is not recognisable.
     */
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
        case "find":
            switch (command) {
            case "todo":
                checkAddCommand();
                return parseTodo();

            case "deadline":
                checkAddCommand();
                return parseDeadline();

            case "event":
                checkAddCommand();
                return parseEvent();

            case "listTime":
                return parseListTime();

            case "find":
                return parseFind();

            default:
                throw new ChattimeException(UNRECOGNISED_COMMAND);
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

    /**
     * Checks user's command clashes with data storage format.
     *
     * @throws ChattimeException Returns error message to require new input with problem statement.
     */
    private void checkCleanCommand() throws ChattimeException {
        if (userInput.contains("@")) {
            throw new ChattimeException("@^@ I'm sorry but your message should not contain any \"@\" .");
        }
    }

    /**
     * Checks required task description input by user.
     *
     * @throws ChattimeException Returns error message to require new input with description provided.
     */
    private void checkAddCommand() throws ChattimeException {
        if (description == null) {
            throw new ChattimeException(String.format(NO_DESCRIPTION, command));
        }
    }

    /**
     * Parses string description into int and checks true int type of inputted description.
     *
     * @return Index parsed if the type check passed.
     * @throws ChattimeException Returns error message to request an integer input for the description.
     */
    private int checkIndexCommand() throws ChattimeException {
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

    /**
     * Processes list command and generate a ListCommand object.
     *
     * @return ListCommand object.
     * @throws ChattimeException If description detected, returns error message to user.
     */
    private ListCommand parseList() throws ChattimeException {
        if (description != null) {
            throw new ChattimeException("OOPS!!! list does not take any description.");
        }

        return new ListCommand(null);
    }

    /**
     * Processes bye command and generate a ByeCommand object.
     *
     * @return ByeCommand object.
     * @throws ChattimeException If ambiguous command detected, returns error message to user.
     */
    private ByeCommand parseBye() throws ChattimeException {
        if (description != null) {
            throw new ChattimeException("Type \"bye\" if you really want to say goodbye to me.");
        }

        return new ByeCommand();
    }

    /**
     * Processes todo command and generate a TodoCommand object.
     *
     * @return TodoCommand object.
     */
    private AddCommand parseTodo() {
        Todo todo = new Todo(description);
        return new AddCommand(todo);
    }

    /**
     * Processes deadline command and generate a DeadlineCommand object.
     *
     * @return DeadlineCommand object.
     * @throws ChattimeException If wrong-formatted input detected, returns error message with instructions to user.
     *
     */
    private AddCommand parseDeadline() throws ChattimeException {
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

    /**
     * Processes event command and generate an EventCommand object.
     *
     * @return EventCommand object.
     * @throws ChattimeException If wrong-formatted input detected, returns error message with instructions to user.
     *
     */
    private AddCommand parseEvent() throws ChattimeException {
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

    /**
     * Processes listTime command, parses description to LocalDate type and generate a ListCommand object.
     *
     * @return ListCommand object.
     * @throws ChattimeException If wrong-formatted input detected, returns error message with instructions to user.
     *
     */
    private ListCommand parseListTime() throws ChattimeException {
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

    /**
     * Processes find command, ensure keyword is inputted and generate a FindCommand object.
     *
     * @return FindCommand object.
     * @throws ChattimeException If no description detected, returns error message to user.
     *
     */
    public FindCommand parseFind() throws ChattimeException {
        if (description == null) {
            throw new ChattimeException(
                    String.format(MISSED_PARAM, command, "find keyword"));
        }
        return new FindCommand(description);
    }

}
