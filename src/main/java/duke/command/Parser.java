package duke.command;

import duke.command.Command;
import duke.exception.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Parser {

    /**
     * Determines whether command is valid and return appropriate <code>Command</code>.
     *
     * @param inputCommand string that to be checked.
     * @return the appropriate Command enum.
     */
    public static Command validateCommand(String inputCommand) {
        Command[] allCommands = Command.values();
        for (int i = 0; i < allCommands.length; i++) {
            if (allCommands[i] == Command.INVALID) {
                break;
            }
            if (allCommands[i].toString().equals(inputCommand.toUpperCase())){
                return allCommands[i];
            }
        }
        return Command.INVALID;
    }

    /**
     * Construct a Todo based on user input.
     *
     * @param input user input to be parsed.
     * @return a todo constructed from user input.
     * @throws DukeException when user input is invalid.
     */
    public static Todo parseTodo(String[] input) throws DukeException { //return task
        if (input.length == 1 || input[1].isEmpty()) {
            throw new DukeException(" ☹ OOPS!!! The description of a todo cannot be empty.");
        }
        Todo tempTodo = new Todo(input[1]);
        return tempTodo;
    }

    /**
     * Construct a Deadline based on user input.
     *
     * @param input user input to be parsed.
     * @return a deadline constructed from user input.
     * @throws DukeException when user input is invalid.
     */
    public static Deadline parseDeadline(String[] input) throws DukeException {
        if (input.length == 1 || input[1].isEmpty()) {
            throw new DukeException(" ☹ OOPS!!! The description and due date of a deadline cannot be empty.");
        }
        String[] dlString = input[1].split(" /by ");
        if (dlString.length == 1 || dlString[1].isEmpty()) {
            throw new DukeException(" ☹ OOPS!!! The description and due date of a deadline cannot be empty.");
        }

        try {
            String[] dateTimeString = dlString[1].split(" ");
            String timeString = "00:00";
            if (dateTimeString.length == 2) {
                timeString = dateTimeString[1].substring(0, 2) + ":" + dateTimeString[1].substring(2);
            }
            LocalDateTime dueDate = LocalDateTime.parse(dateTimeString[0] + "T" + timeString); // format: 2007-12-03T10:15:30
            Deadline tempDeadline = new Deadline(dlString[0], dueDate);
            return  tempDeadline;
        } catch(DateTimeParseException dateTimeParseException) {
            throw new DukeException("Hey! Incorrect format for date-time! Try:  yyyy-mm-dd hhmm");
        }
    }

    /**
     * Construct an Event based on user input.
     *
     * @param input user input to be parsed.
     * @return an event constructed from user input.
     * @throws DukeException when user input is invalid.
     */
    public static Event parseEvent(String[] input) throws DukeException {
        if (input.length == 1 || input[1].isEmpty()) {
            throw new DukeException(" ☹ OOPS!!! The description of an event cannot be empty.");
        }
        String[] eventString = input[1].split(" /from ");
        if (eventString.length == 1 || eventString[1].isEmpty()) {
            throw new DukeException(" ☹ OOPS!!! The start & end time of an event cannot be empty.");
        }
        String[] timeSplit = eventString[1].split(" /to ");
        if (timeSplit.length == 1 || timeSplit[1].isEmpty()) {
            throw new DukeException(" ☹ OOPS!!! The start & end time of an event cannot be empty.");
        }
        try {
            String[] dateTimeString = timeSplit[0].split(" ");
            String startTimeString = "00:00";
            if (dateTimeString.length == 2) {
                startTimeString = dateTimeString[1].substring(0, 2) + ":" + dateTimeString[1].substring(2);
            }
            // format: 2007-12-03T10:15:30
            LocalDateTime startDate = LocalDateTime.parse(dateTimeString[0] + "T" + startTimeString);
            String endTimeString = timeSplit[1].substring(0, 2) + ":" + timeSplit[1].substring(2);
            LocalDateTime endDate = LocalDateTime.parse(dateTimeString[0] + "T" + endTimeString);

            Event tempEvent = new Event(eventString[0], startDate, endDate);
            return tempEvent;

        } catch (DateTimeParseException dateTimeParseException) {
            throw new DukeException("Hey! Incorrect format for date-time! Try:  yyyy-mm-dd hhmm");
        }
    }

    /**
     * Extracts task id from user input
     *
     * @param input user input to be parsed.
     * @return task id to identify task to be marked
     * @throws DukeException when user input is invalid
     */
    public static int parseMarkTask(String[] input) throws DukeException { // returns task id
        if (input.length == 1 || input[1].isEmpty()) {
            throw new DukeException(" ☹ OOPS!!! The item number is required to mark.");
        }
        int taskNum = Integer.parseInt(input[1]);
        return taskNum;
    }

    /**
     * Extracts task id from user input.
     *
     * @param input user input to be parsed.
     * @return task id to identify task to be unmarked.
     * @throws DukeException when user input is invalid.
     */
    public static int parseUnmarkTask(String[] input) throws DukeException { // returns task id
        if (input.length == 1 || input[1].isEmpty()) {
            throw new DukeException(" ☹ OOPS!!! The item number is required to unmark.");
        }
        int taskNum = Integer.parseInt(input[1]);
        return taskNum;
    }

    /**
     * Extracts task id from user input.
     *
     * @param input user input to be parsed.
     * @return task id to identify task to be deleted.
     * @throws DukeException when user input is invalid.
     */
    public static int parseDeleteTask(String[] input) throws DukeException{ // returns task id
        if (input.length == 1 || input[1].isEmpty()) {
            throw new DukeException(" ☹ OOPS!!! The item number is required to delete.");
        }
        int taskNum = Integer.parseInt(input[1]);
        return taskNum;
    }

    public static String parseSearch(String[] input) throws DukeException {
        if (input.length == 1 || input[1].isEmpty()) {
            throw new DukeException("You didn't say what you're looking for.");
        }
        return input[1];
    }
}
