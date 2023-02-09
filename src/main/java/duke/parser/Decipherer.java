package duke.parser;

import java.time.Duration;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.command.AddTaskCommand;
import duke.command.DeleteCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.MarkAsDoneCommand;
import duke.command.UnmarkCommand;
import duke.command.UpdateCommand;
import duke.command.ViewScheduleCommand;
import duke.exception.InvalidInputException;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.FixedDurationTask;
import duke.task.TodoTask;

/**
 * The Decipherer class is responsible for decoding the input string and creating the corresponding command objects.
 * It contains methods for decoding different types of commands, including add, update, mark as done, unmark,
 * delete, find and search. Each method takes in a string as input and returns a command object or throws an
 * InvalidInputException if the input is invalid.
 */
public class Decipherer {
    private static final Pattern emptyStringChecker = Pattern.compile("\\S.*+");
    // Use regular expression to check if the input is a number
    private static final Pattern numberChecker = Pattern.compile("\\d+?");

    private static String[] splitString(String information) {
        return information.split(" ", 2);
    }

    /**
     * Parses the given string index to an int and return the corresponding index
     * in the TaskList, with an offset of -1.
     *
     * @param index The string index of a task
     * @return The corresponding index in the TaskList, with an offset of -1
     */
    private static int getIndex(String index) {
        try {
            //parse the input string as an integer and subtract 1 to convert it to zero-based index
            return Integer.parseInt(index) - 1;
        } catch (NumberFormatException e) {
            //if the input is not a valid integer, return -1
            return -1;
        }
    }

    /**
     * Decodes the information string for an update command.
     * The input string should be in the format of "index description".
     *
     * @param information the string to be decoded
     * @return an UpdateCommand object
     * @throws InvalidInputException if the input task index is not a number or the description is empty
     */
    public static UpdateCommand parseUpdateCommand(String information) throws InvalidInputException {
        //split the input into index and task description
        String[] parts = splitString(information);
        //parse the index and convert it to zero-indexed
        int index = getIndex(parts[0]);

        try {
            //if the task description is empty, throw an exception
            if (parts[1].isEmpty()) {
                throw new InvalidInputException(ErrorMessage.INVALID_DESCRIPTION_ERROR);
            }
            //if the index is invalid, throw an exception
            if (index == -1) {
                throw new InvalidInputException(ErrorMessage.INVALID_TASK_INDEX_ERROR);
            }
            //return a new UpdateCommand object with the index and task description
            return new UpdateCommand(index, parts[1].strip());
        } catch (IndexOutOfBoundsException e) {
            //if there is an index out of bounds exception, throw an exception
            throw new InvalidInputException(ErrorMessage.INVALID_DESCRIPTION_ERROR);
        }
    }

    /**
     * Decodes the information string for a mark as done command.
     * The input string should be in the format of "index".
     *
     * @param information the string to be decoded
     * @return an MarkAsDoneCommand object
     * @throws InvalidInputException if the input task index is not a number
     */
    public static MarkAsDoneCommand parseMarkCommand(String information) throws InvalidInputException {
        if (numberChecker.matcher(information).matches()) {
            // convert the input to an integer and decrement by 1
            return new MarkAsDoneCommand(Integer.parseInt(information) - 1);
        } else {
            // if the input is not a number, throw an exception
            throw new InvalidInputException(ErrorMessage.INVALID_TASK_INDEX_ERROR);
        }
    }

    /**
     * Decodes the information string for an unmark command.
     * The input string should be in the format of "index".
     *
     * @param information the string to be decoded
     * @return an UnmarkCommand object
     * @throws InvalidInputException if the input task index is not a number
     */
    public static UnmarkCommand parseUnmarkCommand(String information) throws InvalidInputException {
        if (numberChecker.matcher(information).matches()) {
            // convert the input to an integer and decrement by 1
            return new UnmarkCommand(Integer.parseInt(information) - 1);
        } else {
            // if the input is not a number, throw an exception
            throw new InvalidInputException(ErrorMessage.INVALID_TASK_INDEX_ERROR);
        }
    }

    /**
     * Decodes the information string for a delete command.
     * The input string should be in the format of "index".
     *
     * @param information the string to be decoded
     * @return a DeleteCommand object
     * @throws InvalidInputException if the input task index is not a number
     */
    public static DeleteCommand parseDeleteCommand(String information) throws InvalidInputException {
        if (numberChecker.matcher(information).matches()) {
            // convert the input to an integer and decrement by 1
            return new DeleteCommand(Integer.parseInt(information) - 1);
        } else {
            // if the input is not a number, throw an exception
            throw new InvalidInputException(ErrorMessage.INVALID_TASK_INDEX_ERROR);
        }
    }

    /**
     * Decodes the information string for finding tasks with certain descriptions.
     *
     * @param information the string to be decoded
     * @return a FindCommand object
     * @throws InvalidInputException if the input task description is empty
     */
    public static FindCommand parseFindCommand(String information) throws InvalidInputException {
        // check if the input is not empty
        if (!emptyStringChecker.matcher(information).matches()) {
            throw new InvalidInputException(ErrorMessage.INVALID_DESCRIPTION_ERROR);
        } else {
            String[] descriptions = information.split(" ");
            // create a new FindCommand with the array of descriptions
            return new FindCommand(descriptions);
        }
    }

    /**
     * Parses the help command from the user input string.
     *
     * @param information the user input string.
     * @return the parsed help command.
     * @throws InvalidInputException if the information is not in a valid format.
     */
    public static HelpCommand parseHelpCommand(String information) throws InvalidInputException {
        if (!emptyStringChecker.matcher(information).matches()) {
            // If the information is an empty string, return a help command with the normal mode
            return new HelpCommand("normal");
        } else {
            // If the information is not an empty string, return a help command with the information as its mode
            return new HelpCommand(information);
        }
    }

    /**
     * Decodes the information string for finding tasks on certain date.
     *
     * @param information the string to be decoded
     * @return a ViewScheduleCommand object
     * @throws InvalidInputException if the input task description is empty, or the input date format is invalid
     */
    public static ViewScheduleCommand parseViewCommand(String information) throws InvalidInputException {
        // check if the input is not empty
        if (!emptyStringChecker.matcher(information).matches()) {
            throw new InvalidInputException(ErrorMessage.INVALID_DESCRIPTION_ERROR);
        } else {
            try {
                // create a new ViewScheduleCommand with the date
                return new ViewScheduleCommand(TimeHandler.parseToLocalDate(information));
            } catch (DateTimeParseException e) {
                // if the input date format is invalid, throw an exception
                throw new InvalidInputException(ErrorMessage.INVALID_DATE_ERROR);
            }
        }
    }

    /**
     * Decodes the information string for a todo task.
     * The input string should be in the format of "description".
     *
     * @param information the string to be decoded
     * @return an AddTaskCommand object
     * @throws InvalidInputException if the input task description is empty
     */
    public static AddTaskCommand parseTodoCommand(String information) throws InvalidInputException {
        // check if the input is not empty
        if (!emptyStringChecker.matcher(information).matches()) {
            throw new InvalidInputException(ErrorMessage.EMPTY_TODO_ERROR);
        } else {
            // create a new TodoTask and return an AddTaskCommand with it
            return new AddTaskCommand(new TodoTask(information));
        }
    }

    /**
     * Parses the input string and creates an `AddTaskCommand` for a deadline task.
     *
     * @param information  the input string
     * @return an `AddTaskCommand` for a deadline task
     * @throws InvalidInputException if the input string is empty or in an incorrect format
     */
    public static AddTaskCommand parseDeadlineCommand(String information) throws InvalidInputException {
        // Check if the input string is empty
        if (!emptyStringChecker.matcher(information).matches()) {
            throw new InvalidInputException(ErrorMessage.EMPTY_DEADLINE_ERROR);
        }

        // Extract the name and deadline date from the input string
        Matcher dateChecker = extractNameAndDate(information);

        // If the input string is in the correct format
        if (dateChecker.matches()) {
            // Create an `AddTaskCommand` for a deadline task
            return createDeadlineTaskCommand(dateChecker);
        } else {
            // If the input string is in an incorrect format, throw an exception
            throw new InvalidInputException(ErrorMessage.INVALID_DEADLINE_FORMAT_ERROR);
        }
    }

    /**
     * Extracts the name and deadline date from the input string.
     *
     * @param information input string
     * @return a Matcher object that matches the extracted name and deadline date
     */
    private static Matcher extractNameAndDate(String information) {
        // Use regular expression to extract the name and deadline date
        return Pattern.compile("(?<name>.*)/by\\s*(?<date>.*)").matcher(information);
    }

    /**
     * Creates an {@link AddTaskCommand} with a {@link DeadlineTask} based on the extracted name and date.
     *
     * @param dateChecker The {@link Matcher} object containing the extracted information from the input string.
     * @return An {@link AddTaskCommand} containing the created {@link DeadlineTask}.
     * @throws InvalidInputException if the date format is incorrect.
     */
    private static AddTaskCommand createDeadlineTaskCommand(Matcher dateChecker) throws InvalidInputException {
        // Extract the name and date from the Matcher object
        String name = dateChecker.group("name").trim();
        String date = dateChecker.group("date").trim();
        try {
            // Create a new DeadlineTask and return an AddTaskCommand with it
            return new AddTaskCommand(new DeadlineTask(name, TimeHandler.parseToLocalDateTime(date)));
        } catch (DateTimeParseException e) {
            // If the date format is incorrect, throw an exception
            throw new InvalidInputException(ErrorMessage.INVALID_DATETIME_ERROR);
        }
    }

    /**
     * Parses the information to an Event task command.
     *
     * @param information the string to parse
     * @return the event task command
     * @throws InvalidInputException if the input is not in the correct format
     */
    public static AddTaskCommand parseEventCommand(String information) throws InvalidInputException {
        // Check if the input string is empty
        if (!emptyStringChecker.matcher(information).matches()) {
            throw new InvalidInputException(ErrorMessage.EMPTY_EVENT_ERROR);
        }

        // Extract the task name and time interval
        Matcher intervalChecker = extractNameAndInterval(information);

        // If the task name and time interval are extracted successfully, create a new event task command
        if (intervalChecker.matches()) {
            return createEventTaskCommand(intervalChecker);
        } else {
            // Otherwise, throw an exception for invalid format
            throw new InvalidInputException(ErrorMessage.INVALID_EVENT_FORMAT_ERROR);
        }
    }

    /**
     * Extracts the name and interval from the information string using a regular expression pattern.
     *
     * @param information The information string to be processed.
     * @return A Matcher object that matches the information string to the pattern.
     */
    private static Matcher extractNameAndInterval(String information) {
        // Extract the task name and interval using the pattern "(?<name>.*)/from(?<from>.*)/to(?<to>.*)"
        return Pattern.compile("(?<name>.*)/from(?<from>.*)/to(?<to>.*)").matcher(information);
    }

    /**
     * Creates an {@code AddTaskCommand} that adds an event task.
     *
     * @param intervalChecker the regex matcher that matches the name and interval of the task
     * @return the created {@code AddTaskCommand}
     * @throws InvalidInputException if the date and time specified in the interval are invalid
     */
    private static AddTaskCommand createEventTaskCommand(Matcher intervalChecker) throws InvalidInputException {
        // Extract the name and interval of the task from the matcher
        String name = intervalChecker.group("name").trim();
        String from = intervalChecker.group("from").trim();
        String to = intervalChecker.group("to").trim();

        try {
            // Create a new event task with the extracted information and return the corresponding AddTaskCommand
            return new AddTaskCommand(new EventTask(name,
                    TimeHandler.parseToLocalDateTime(from), TimeHandler.parseToLocalDateTime(to)));
        } catch (DateTimeParseException e) {
            // If the date and time specified in the interval are invalid, throw an InvalidInputException
            throw new InvalidInputException(ErrorMessage.INVALID_DATETIME_ERROR);
        }
    }

    /**
     * Parses the input string for a fixed-duration task description, checks for its validity,
     * and returns a command to add the task if it is valid.
     *
     * @param information the string to be parsed
     * @return the command to add the task
     * @throws InvalidInputException if the input string is invalid
     */
    public static AddTaskCommand parseFixedDurationCommand(String information) throws InvalidInputException {
        // check if the input string is empty
        if (!emptyStringChecker.matcher(information).matches()) {
            throw new InvalidInputException(ErrorMessage.EMPTY_FIXED_DURATION_DESCRIPTION_ERROR);
        }

        // extract the task name and duration from the input string
        Matcher durationChecker = extractNameAndDuration(information);
        if (durationChecker.matches()) {
            return createFixedDurationTaskCommand(durationChecker);
        } else {
            throw new InvalidInputException(ErrorMessage.INVALID_FIXED_DURATION_FORMAT_ERROR);
        }
    }

    /**
     * Extract the name and duration of a fixed duration task from the input information string.
     *
     * @param information - the input information string that contains the task name and duration
     * @return a Matcher object that can be used to extract the name and duration of the task
     */
    private static Matcher extractNameAndDuration(String information) {
        return Pattern.compile("(?<name>.*)/within\\s*(?<duration>.*)").matcher(information);
    }

    /**
     * Creates a new {@link AddTaskCommand} containing a {@link FixedDurationTask} based on the parsed information.
     *
     * @param durationChecker a {@link Matcher} that matches the name and duration of the task
     * @return a new {@link AddTaskCommand} containing a {@link FixedDurationTask}
     * @throws InvalidInputException if there is an error with the format of the input information
     */
    private static AddTaskCommand createFixedDurationTaskCommand(Matcher durationChecker) throws InvalidInputException {
        String name = durationChecker.group("name").trim(); // Retrieve the name of the task
        String duration = durationChecker.group("duration").trim(); // Retrieve the duration of the task
        try {
            return new AddTaskCommand(new FixedDurationTask(name, Duration.parse(duration)));
        } catch (DateTimeParseException e) {
            throw new InvalidInputException(ErrorMessage.INVALID_DURATION_FORMAT);
        }
    }
}
