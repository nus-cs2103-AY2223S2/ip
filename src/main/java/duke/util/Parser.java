package duke.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.command.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Represents a parser that parses user input into commands.
 */
public class Parser {

    private static final DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");

    private String readCommand(String[] userCommand) {
        return userCommand[0];
    }

    private int queryInteger(String[] userCommand) throws DukeException {
        if (isInteger(userCommand)) {
            return Integer.parseInt(userCommand[1]);
        } else {
            throw new DukeException("error Invalid formatting for commands in queryInteger");
        }
    }

    private static boolean isInteger(String[] userCommand) throws DukeException {
        if (userCommand.length != 2) {
            throw new DukeException("error Invalid formatting for commands in isInteger");
        }
        return isInteger(userCommand[1], 10);
    }

    private static boolean isInteger(String s, int radix) {
        if (s.isEmpty()) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            if (i == 0 && s.charAt(i) == '-') {
                if (s.length() == 1) {
                    return false;
                } else {
                    continue;
                }
            }
            if (Character.digit(s.charAt(i), radix) < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the command after parsing the user input.
     *
     * @param userCommand The user input.
     * @return Command to be executed.
     * @throws DukeException If there is an error in parsing the user input.
     */
    public Command parse(String[] userCommand) throws DukeException {
        String name;
        //checkCommandValidity(userCommand.clone());
        switch(this.readCommand(userCommand)) {
        case "todo":
            return new TodoCommand(userCommand[1]);

        case "deadline":
            String[] splitBy = userCommand[1].split(" /by ", 2);
            name = splitBy[0];
            String by = splitBy[1];
            LocalDateTime byTime = LocalDateTime.parse(by, DATETIME_FORMAT);
            return new DeadlineCommand(name, byTime);

        case "event":
            String[] splitFrom = userCommand[1].split(" /from ", 2);
            String[] splitTo = splitFrom[1].split(" /to ", 2);
            name = splitFrom[0];
            String from = splitTo[0];
            String to = splitTo[1];
            LocalDateTime fromTime = LocalDateTime.parse(from, DATETIME_FORMAT);
            LocalDateTime toTime = LocalDateTime.parse(to, DATETIME_FORMAT);
            return new EventCommand(name, fromTime, toTime);

        case "mark":
            return new MarkCommand(this.queryInteger(userCommand), true);

        case "unmark":
            return new MarkCommand(this.queryInteger(userCommand), false);

        case "list":
            return new ListCommand();

        case "bye":
            return new ExitCommand();

        case "delete":
            return new DeleteCommand(this.queryInteger(userCommand));

        case "find":
            String keyword = userCommand[1];
            return new FindCommand(keyword);

        case "edit":
            String[] splitTask = userCommand[1].split(" ", 2);
            Task task = parseTask(splitTask[1].split(" ", 2));
            int taskNumber = queryInteger(new String[]{"edit ", splitTask[0]});
            return new EditCommand(taskNumber, task);

        default:
            throw new DukeException("Cannot recognise command!");
        }
    }

    private void checkCommandValidity(String[] userCommandClone) throws DukeException {
        switch(this.readCommand(userCommandClone)) {
        case "todo":

        case "find":
            if (userCommandClone.length == 1) {
                throw new DukeException("error no arguments");
            }
            break;

        case "deadline":
            if (userCommandClone.length == 1) {
                throw new DukeException("error no arguments");
            }
            String[] splitBy = userCommandClone[1].split(" /by ", 2);
            if (splitBy.length != 2) {
                throw new DukeException("error Invalid formatting for commands");
            }
            break;

        case "event":
            if (userCommandClone.length == 1) {
                throw new DukeException("error no arguments");
            }
            String[] splitFrom = userCommandClone[1].split(" /from ", 2);
            if (splitFrom.length != 2) {
                throw new DukeException("error Invalid formatting for commands");
            }
            String[] splitTo = splitFrom[1].split(" /to ", 2);
            if (splitTo.length != 2) {
                throw new DukeException("error Invalid formatting for commands");
            }
            break;

        case "list":

        case "bye":
            if (userCommandClone.length > 1) {
                throw new DukeException("error Invalid formatting for commands");
            }
            break;

        case "edit":
            if (userCommandClone.length == 1) {
                throw new DukeException("error Invalid formatting for commands in validity1");
            }
            System.out.println(userCommandClone[1]);
            String[] splitTask = userCommandClone[1].split(" ", 2);
            System.out.println(splitTask[0]);
            if (splitTask.length == 1) {
                throw new DukeException("error Invalid formatting for commands in validity2");
            }
            break;
        default:
        }
    }

    /**
     * A method which helps to get the Task out from the user command.
     *
     * @param userCommand Input user command
     * @return Task the input user command changed into one of the tasks for the edit command.
     * @throws DukeException in case it is not a command that can be used.
     */
    private Task parseTask(String[] userCommand) throws DukeException {
        String name;
        checkCommandValidity(userCommand);
        switch(this.readCommand(userCommand)) {
        case "todo":
            return new Todo(userCommand[1]);

        case "deadline":
            String[] splitBy = userCommand[1].split(" /by ", 2);
            name = splitBy[0];
            String by = splitBy[1];
            LocalDateTime byTime = LocalDateTime.parse(by, DATETIME_FORMAT);
            return new Deadline(name, byTime);

        case "event":
            String[] splitFrom = userCommand[1].split(" /from ", 2);
            String[] splitTo = splitFrom[1].split(" /to ", 2);
            name = splitFrom[0];
            String from = splitTo[0];
            String to = splitTo[1];
            LocalDateTime fromTime = LocalDateTime.parse(from, DATETIME_FORMAT);
            LocalDateTime toTime = LocalDateTime.parse(to, DATETIME_FORMAT);
            return new Event(name, fromTime, toTime);

        default:
            throw new DukeException("Needs to be a command that gives a task MEOWW!");
        }
    }
}
