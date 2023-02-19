package duke.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.exception.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;


/**
 * Processes user input.
 */
public class Parser {

    /**
     * Validates user input and returns the appropriate <code>Command</code>.
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
            if (allCommands[i].toString().equals(inputCommand.toUpperCase())) {
                return allCommands[i];
            }
        }
        return Command.INVALID;
    }

    /**
     * Returns a todo based on user input.
     *
     * @param input user input to be parsed.
     * @return a todo constructed from user input.
     * @throws DukeException when user input is invalid.
     */
    public static Todo parseTodo(String[] input) throws DukeException { //return task
        boolean descriptionMissing = input.length == 1 || input[1].isEmpty();
        if (descriptionMissing) {
            throw new DukeException("Hey!!! The description of a todo cannot be empty.");
        }
        Todo tempTodo = new Todo(input[1]);
        return tempTodo;
    }

    /**
     * Returns a Deadline based on user input.
     *
     * @param input user input to be parsed.
     * @return a deadline constructed from user input.
     * @throws DukeException when user input is invalid.
     */
    public static Deadline parseDeadline(String[] input) throws DukeException {
        boolean inputIsTooShort = input.length == 1 || input[1].isEmpty();
        if (inputIsTooShort) {
            throw new DukeException("Hey!!! The description and due date of a deadline cannot be empty.");
        }
        String[] dlString = input[1].split(" /by ");
        boolean dateIsMissing = dlString.length == 1 || dlString[1].isEmpty();
        if (dateIsMissing) {
            throw new DukeException("Hey!!! The description and due date of a deadline cannot be empty.");
        }

        try {
            String[] dateTimeString = dlString[1].split(" ");
            String timeString = "00:00";
            if (dateTimeString.length == 2) {
                timeString = dateTimeString[1].substring(0, 2) + ":" + dateTimeString[1].substring(2);
            }
            // format: 2007-12-03T10:15:30
            LocalDateTime dueDate = LocalDateTime.parse(dateTimeString[0] + "T" + timeString);
            Deadline tempDeadline = new Deadline(dlString[0], dueDate);
            return tempDeadline;
        } catch (DateTimeParseException dateTimeParseException) {
            throw new DukeException("Hey! Incorrect format for date-time! Try:  yyyy-mm-dd hhmm");
        }
    }

    /**
     * Returns an Event based on user input.
     *
     * @param input user input to be parsed.
     * @return an event constructed from user input.
     * @throws DukeException when user input is invalid.
     */
    public static Event parseEvent(String[] input) throws DukeException {
        boolean inputIsTooShort = input.length == 1 || input[1].isEmpty();
        if (inputIsTooShort) {
            throw new DukeException("Hey!!! The description of an event cannot be empty.");
        }
        String emptyDateMsg = "Hey!!! The start & end time of an event cannot be empty.";
        String[] eventDetails = input[1].split(" /from ");
        boolean bothDateMissing = eventDetails.length == 1 || eventDetails[1].isEmpty();
        if (bothDateMissing) {
            throw new DukeException(emptyDateMsg);
        }
        String[] timeSplit = eventDetails[1].split(" /to ");
        boolean endDateMissing = timeSplit.length == 1 || timeSplit[1].isEmpty();
        if (endDateMissing) {
            throw new DukeException(emptyDateMsg);
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

            Event tempEvent = new Event(eventDetails[0], startDate, endDate);
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
    public static ArrayList<Integer> parseMarkTask(String[] input) throws DukeException { // returns task id
        boolean inputIsTooShort = input.length == 1 || input[1].isEmpty();
        if (inputIsTooShort) {
            throw new DukeException("Hey!!! The item number is required to mark.");
        }

        String[] inputIds = input[1].split(" ");
        ArrayList<Integer> taskIds = new ArrayList<>();
        for (int i = 0; i < inputIds.length; i++) {
            try {
                int id = Integer.parseInt(inputIds[i]) - 1;
                taskIds.add(id);
            } catch (NumberFormatException numberFormatException) {
                throw new DukeException("Seems like one of them is not a number.");
            }
        }

        return taskIds;
    }

    /**
     * Extracts task id from user input.
     *
     * @param input user input to be parsed.
     * @return task id to identify task to be unmarked.
     * @throws DukeException when user input is invalid.
     */
    public static ArrayList<Integer> parseUnmarkTask(String[] input) throws DukeException { // returns task id
        boolean inputIsTooShort = input.length == 1 || input[1].isEmpty();
        if (inputIsTooShort) {
            throw new DukeException("Hey!!! The item number is required to unmark.");
        }


        String[] inputIds = input[1].split(" ");

        ArrayList<Integer> taskIds = new ArrayList<>();
        for (int i = 0; i < inputIds.length; i++) {
            try {
                int id = Integer.parseInt(inputIds[i]) - 1;
                taskIds.add(id);
            } catch (NumberFormatException numberFormatException) {
                throw new DukeException("Seems like one of them is not a number.");
            }
        }


        return taskIds;
    }

    /**
     * Extracts task id from user input.
     *
     * @param input user input to be parsed.
     * @return task id to identify task to be deleted.
     * @throws DukeException when user input is invalid.
     */
    public static ArrayList<Integer> parseDeleteTask(String[] input) throws DukeException { // returns task id
        boolean inputIsTooShort = input.length == 1 || input[1].isEmpty();
        if (inputIsTooShort) {
            throw new DukeException("Hey!!! The item number is required to delete.");
        }


        String[] inputIds = input[1].split(" ");

        ArrayList<Integer> taskIds = new ArrayList<>();
        for (int i = 0; i < inputIds.length; i++) {
            try {
                int id = Integer.parseInt(inputIds[i]) - 1;
                taskIds.add(id);
            } catch (NumberFormatException numberFormatException) {
                throw new DukeException("Seems like one of them is not a number.");
            }
        }

        return taskIds;
    }

    /**
     * Extracts search term from user input
     * @param input user input to eb parsed
     * @return the extracted search term.
     * @throws DukeException when user input is invalid.
     */
    public static String parseSearch(String[] input) throws DukeException {
        boolean inputIsTooShort = input.length == 1 || input[1].isEmpty();
        if (inputIsTooShort) {
            throw new DukeException("You didn't say what you're looking for.");
        }

        return input[1];
    }
}
