package chattime.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

import chattime.command.AddCommand;
import chattime.command.ByeCommand;
import chattime.command.Command;
import chattime.command.DeleteCommand;
import chattime.command.FindCommand;
import chattime.command.HelpCommand;
import chattime.command.ListCommand;
import chattime.command.MarkCommand;
import chattime.command.ScheduleCommand;
import chattime.exception.ChattimeException;
import chattime.task.Deadline;
import chattime.task.Event;
import chattime.task.Task;
import chattime.task.Todo;


/**
 * Represents parser for bot, processes raw user input and creates appropriate Command object.
 */
public class Parser {

    private static final String NO_DESCRIPTION = "The description of %s cannot be empty.";
    private static final String MISSED_PARAM = "%s should be in the form of\n%s.";
    private static final String NO_INDEX = "The index to %1$s cannot be empty.";
    private static final String NEED_INT = "The index to %1$s must be positive integer.";
    private static final String IDX_OUT_OF_BOUND = "The index is invalid! We currently have %d task(s).";
    private static final String UNRECOGNISED_COMMAND = "Sorry... but I don't understand what you said >,<\n\n"
            + "Type `help` if you need me!";
    private static final String CLEAN_COMMAND_ALERT = "@^@ I'm sorry but your message should not contain any \"@\" .";
    private static final String DEADLINE_FORMAT = "deadline (task) /by (yyyy-mm-dd hh:mm)";
    private static final String EVENT_FORMAT = "event (task) /from (yyyy-mm-dd hh:mm) /to (yyyy-mm-dd hh:mm)";
    private static final String DATETIME_FORMAT = "Please enter both date and time in format yyyy-mm-dd hh:mm"
            + " or yyyy-mm-dd";

    private static String userInput;
    private static String[] splitCommand;
    private static String command;
    private static String description;

    /**
     * Returns suitable Command object for further execution.
     *
     * @param input The command and description entered by user.
     * @return The command object to execute user request.
     * @throws ChattimeException If command is not recognisable.
     */
    public static Command parse(String input) throws ChattimeException {
        parseInput(input);
        checkCleanCommand();
        switch (command) {
        case "bye":
            return parseBye();
        case "help":
            return parseHelp();
        case "list":
            return parseList();
        case "listTime":
            return parseTime(false);
        case "schedule":
            return parseTime(true);
        case "find":
            return parseFind();
        case "todo":
        case "deadline":
        case "event":
            return parseAddCommand();
        case "mark":
        case "unmark":
        case "delete":
            return parseIndexCommand();
        default:
            throw new ChattimeException(UNRECOGNISED_COMMAND);
        }
    }

    /**
     * Processes user input into command and description.
     *
     * @param input The command and description entered by user.
     */
    private static void parseInput(String input) {
        userInput = input.trim();
        splitCommand = userInput.split(" ", 2);
        command = splitCommand[0].trim();

        if (splitCommand.length > 1) {
            description = splitCommand[1].trim();
        } else {
            description = null;
        }
    }

    /**
     * Checks user's command clashes with data storage format.
     *
     * @throws ChattimeException If input contains `@`.
     */
    private static void checkCleanCommand() throws ChattimeException {
        if (userInput.contains("@")) {
            throw new ChattimeException(CLEAN_COMMAND_ALERT);
        }
    }

    /**
     * Checks required task description input by user.
     *
     * @throws ChattimeException If no description provided in the input.
     */
    private static void checkAddCommand() throws ChattimeException {
        if (description == null) {
            throw new ChattimeException(String.format(NO_DESCRIPTION, command));
        }
    }

    /**
     * Processes user command for type 'add task' e.g. todo, deadline and event.
     *
     * @return The Add type command due to user input.
     * @throws ChattimeException If command format is not found.
     */
    private static Command parseAddCommand() throws ChattimeException {
        checkAddCommand();

        if (command.equals("todo")) {
            return parseTodo();

        } else if (command.equals("deadline")) {
            return parseDeadline();

        } else {
            return parseEvent();
        }
    }

    /**
     * Processes user command relating to index input e.g. mark, unmark and delete.
     *
     * @return The index type commands due to user input.
     * @throws ChattimeException If command format is not found.
     */
    private static Command parseIndexCommand() throws ChattimeException {
        int index = checkIndexCommand();

        if (command.equals("mark")) {
            return new MarkCommand(index, true);

        } else if (command.equals("unmark")) {
            return new MarkCommand(index, false);

        } else {
            return new DeleteCommand(index);
        }
    }

    /**
     * Parses string description into int and checks true int type of inputted description.
     *
     * @return The index parsed if the type check passed.
     * @throws ChattimeException If description is not integer.
     */
    private static int checkIndexCommand() throws ChattimeException {
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
     * Processes list command and generates a ListCommand object.
     *
     * @return The ListCommand object.
     * @throws ChattimeException If description is detected.
     */
    private static ListCommand parseList() throws ChattimeException {
        if (description != null) {
            throw new ChattimeException("list does not take any description.");
        }

        return new ListCommand(null);
    }

    /**
     * Processes help command and generate a HelpCommand object.
     * Do not throw any exception as long as the command is 'help'.
     *
     * @return The HelpCommand object.
     */
    private static HelpCommand parseHelp() {
        return new HelpCommand();
    }

    /**
     * Processes bye command and generates a ByeCommand object.
     *
     * @return The ByeCommand object.
     * @throws ChattimeException If ambiguous command is detected.
     */
    private static ByeCommand parseBye() throws ChattimeException {
        if (description != null) {
            throw new ChattimeException("Type \"bye\" if you really want to say goodbye to me :(");
        }

        return new ByeCommand();
    }

    /**
     * Processes todo command and generates a TodoCommand object.
     *
     * @return The TodoCommand object.
     */
    private static AddCommand parseTodo() {
        Todo todo = new Todo(description);
        return new AddCommand(todo);
    }

    /**
     * Processes deadline command and generates a DeadlineCommand object.
     *
     * @return The DeadlineCommand object.
     * @throws ChattimeException If wrong-formatted input is detected.
     */
    private static AddCommand parseDeadline() throws ChattimeException {
        String[] splitBy = description.split(" /by ", 2);

        checkDescriptionFormat(splitBy, DEADLINE_FORMAT);

        return createAddDeadlineCmd(splitBy);
    }

    private static AddCommand createAddDeadlineCmd(String[] splitBy) throws ChattimeException {
        String task = splitBy[0].trim();
        Deadline deadlineTask;

        try {
            String[] time = splitBy[1].split(" ", 2);
            LocalDate byDate = LocalDate.parse(time[0].trim());
            if (time.length == 1) {
                deadlineTask = new Deadline(task, byDate);
            } else {
                LocalTime byTime = LocalTime.parse(time[1].trim());
                deadlineTask = new Deadline(task, byDate, byTime);
            }

            return new AddCommand(deadlineTask);

        } catch (DateTimeParseException e) {
            throw new ChattimeException(DATETIME_FORMAT);
        }
    }

    /**
     * Processes event command and generates an EventCommand object.
     *
     * @return The EventCommand object.
     * @throws ChattimeException If wrong-formatted input is detected.
     */
    private static AddCommand parseEvent() throws ChattimeException {
        String[] splitTask = description.split(" /from ", 2);
        String task = splitTask[0].trim();

        checkDescriptionFormat(splitTask, EVENT_FORMAT);

        String[] splitFrom = splitTask[1].split(" /to ", 2);

        checkDescriptionFormat(splitFrom, EVENT_FORMAT);

        return createAddEventCmd(task, splitFrom);
    }

    private static AddCommand createAddEventCmd(String task, String[] splitFrom) throws ChattimeException {
        try {
            String[] from = splitFrom[0].split(" ", 2);
            String[] to = splitFrom[1].split(" ", 2);

            LocalDate fromDate = LocalDate.parse(from[0].trim());
            LocalTime fromTime;
            if (from.length == 1) {
                fromTime = LocalTime.of(0, 0);
            } else {
                fromTime = LocalTime.parse(from[1].trim());
            }

            LocalDate toDate = LocalDate.parse(to[0].trim());
            LocalTime toTime;
            if (to.length == 1) {
                toTime = LocalTime.of(23, 59);
            } else {
                toTime = LocalTime.parse(to[1].trim());
            }

            checkDateValidity(fromDate, fromTime, toDate, toTime);
            Event eventTask = new Event(task, fromDate, fromTime, toDate, toTime);
            return new AddCommand(eventTask);

        } catch (DateTimeParseException e) {
            throw new ChattimeException(DATETIME_FORMAT);
        }
    }

    private static void checkDateValidity(LocalDate fromDate, LocalTime fromTime, LocalDate toDate, LocalTime toTime)
            throws ChattimeException {
        LocalDateTime start = LocalDateTime.of(fromDate, fromTime);
        LocalDateTime end = LocalDateTime.of(toDate, toTime);

        if (start.isAfter(end)) {
            throw new ChattimeException("The event can't start after it was ended!");
        }
    }

    /**
     * Checks parsed command is of the right format.
     *
     * @param split The processed command.
     * @throws ChattimeException If wrong-formatted input is detected.
     */
    private static void checkDescriptionFormat(String[] split, String format) throws ChattimeException {
        if (split.length < 2 || split[1].equals("")) {
            throw new ChattimeException(String.format(MISSED_PARAM, command, format));
        }
    }

    /**
     * Processes listTime command, parses description to LocalDate type and generates a ListCommand object
     * or a View Schedules object.
     *
     * @return The ListCommand object or ViewSchedules object.
     * @throws ChattimeException If wrong-formatted input is detected.
     */
    private static Command parseTime(boolean isScheduleRequired) throws ChattimeException {
        if (description == null) {
            throw new ChattimeException(
                    String.format(MISSED_PARAM, command, "date yyyy-mm-dd"));
        }

        try {
            LocalDate date = LocalDate.parse(description);
            return (isScheduleRequired ? new ScheduleCommand(date) : new ListCommand(date));

        } catch (DateTimeParseException e) {
            throw new ChattimeException(DATETIME_FORMAT);
        }
    }

    /**
     * Processes find command, ensures keyword is inputted and generates a FindCommand object.
     *
     * @return The FindCommand object.
     * @throws ChattimeException If wrong-formatted input is detected.
     */
    public static FindCommand parseFind() throws ChattimeException {
        if (description == null) {
            throw new ChattimeException(
                    String.format(MISSED_PARAM, command, "find keyword"));
        }
        return new FindCommand(description);
    }

}
