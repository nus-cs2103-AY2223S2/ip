package duke.parser;

import duke.command.AddTaskCommand;
import duke.command.Command;
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

import java.time.Duration;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Decipherer class is responsible for decoding the input string and creating the corresponding command objects.
 * It contains methods for decoding different types of commands, including add, update, mark as done, unmark,
 * delete, find and search. Each method takes in a string as input and returns a command object or throws an
 * InvalidInputException if the input is invalid.
 * /
 */
public class Decipherer {
    private static final Pattern emptyStringChecker = Pattern.compile("\\S.*+");

    private static String[] splitString(String information) {
        return information.split(" ", 2);
    }

    /**
     * Helper method to parse the given string index to an int and return the corresponding index
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
        // Use regular expression to check if the input is a number
        Matcher numberChecker = Pattern.compile("\\d+?").matcher(information);

        if (numberChecker.matches()) {
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
        // Use regular expression to check if the input is a number
        Matcher numberChecker = Pattern.compile("\\d+?").matcher(information);

        if (numberChecker.matches()) {
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
        // Use regular expression to check if the input is a number
        Matcher numberChecker = Pattern.compile("\\d+?").matcher(information);

        if (numberChecker.matches()) {
            // convert the input to an integer and decrement by 1
            return new DeleteCommand(Integer.parseInt(information) - 1);
        } else {
            // if the input is not a number, throw an exception
            throw new InvalidInputException(ErrorMessage.INVALID_TASK_INDEX_ERROR);
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
     * Decodes the information string for a deadline task.
     * The input string should be in the format of "description /by date"
     *
     * @param information the string to be decoded
     * @return an AddTaskCommand object
     * @throws InvalidInputException if the input task description is empty,
     * the input date format is invalid, or the input format is incorrect
     */
    public static AddTaskCommand parseDeadlineCommand(String information) throws InvalidInputException {
        // check if the input is not empty
        if (!emptyStringChecker.matcher(information).matches()) {
            throw new InvalidInputException(ErrorMessage.EMPTY_DEADLINE_ERROR);
        } else {
            // Use regular expression to extract the name and date
            Matcher dateChecker = Pattern.compile("(?<name>.*)/by\\s*(?<date>.*)").matcher(information);

            if (dateChecker.matches()) {
                String name = dateChecker.group("name").trim();
                String date = dateChecker.group("date").trim();
                try {
                    // create a new DeadlineTask and return an AddTaskCommand with it
                    return new AddTaskCommand(new DeadlineTask(name, TimeHandler.parseToLocalDateTime(date)));
                } catch (DateTimeParseException e) {
                    // if the input date format is invalid, throw an exception
                    throw new InvalidInputException(ErrorMessage.INVALID_DATETIME_ERROR);
                }
            } else {
                // if the input format is incorrect, throw an exception
                throw new InvalidInputException(ErrorMessage.INVALID_DEADLINE_FORMAT_ERROR);
            }
        }
    }

    /**
     * Decodes the information string for an event task.
     * The input string should be in the format of "description /from start_date /to end_date"
     *
     * @param information the string to be decoded
     * @return an AddTaskCommand object
     * @throws InvalidInputException if the input task description is empty,
     * the input date format is invalid, or the input format is incorrect
     */
    public static AddTaskCommand parseEventCommand(String information) throws InvalidInputException {
        // check if the input is not empty
        if (!emptyStringChecker.matcher(information).matches()) {
            throw new InvalidInputException(ErrorMessage.EMPTY_EVENT_ERROR);
        } else {
            // Use regular expression to extract the name, start date, and end date
            Matcher intervalChecker = Pattern.compile("(?<name>.*)/from(?<from>.*)/to(?<to>.*)")
                    .matcher(information);
            if (intervalChecker.matches()) {
                String name = intervalChecker.group("name").trim();
                String from = intervalChecker.group("from").trim();
                String to = intervalChecker.group("to").trim();
                try {
                    // create a new EventTask and return an AddTaskCommand with it
                    return new AddTaskCommand(
                            new EventTask(name, TimeHandler.parseToLocalDateTime(from), TimeHandler.parseToLocalDateTime(to)));
                } catch (DateTimeParseException e) {
                    // if the input date format is invalid, throw an exception
                    throw new InvalidInputException(ErrorMessage.INVALID_DATETIME_ERROR);
                }
            } else {
                // if the input format is incorrect, throw an exception
                throw new InvalidInputException(ErrorMessage.INVALID_EVENT_FORMAT_ERROR);
            }
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
     * Decodes the information string for finding tasks on certain date.
     *
     * @param information the string to be decoded
     * @return a ViewScheduleCommand object
     * @throws InvalidInputException if the input task description is empty,
     * or the input date format is invalid
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

    public static HelpCommand parseHelpCommand(String information) throws InvalidInputException {
        if (!emptyStringChecker.matcher(information).matches()) {
            return new HelpCommand("normal");
        } else {
            return new HelpCommand(information);
        }
    }

    public static Command parseFixedDurationCommand(String information) throws InvalidInputException {
        if (!emptyStringChecker.matcher(information).matches()) {
            throw new InvalidInputException(ErrorMessage.EMPTY_FIXED_DURATION_DESCRIPTION_ERROR);
        } else {
            // Use regular expression to extract the name and duration
            Matcher durationChecker = Pattern.compile("(?<name>.*)/within\\s*(?<duration>.*)").matcher(information);

            if (durationChecker.matches()) {
                String name = durationChecker.group("name").trim();
                String duration = durationChecker.group("duration").trim();
                try {
                    // create a new FixedDurationTask and return an AddTaskCommand with it
                    return new AddTaskCommand(new FixedDurationTask(name, Duration.parse(duration)));
                } catch (DateTimeParseException e) {
                    throw new InvalidInputException(ErrorMessage.INVALID_DURATION_FORMAT);
                }
            } else {
                // if the input format is incorrect, throw an exception
                throw new InvalidInputException(ErrorMessage.INVALID_FIXED_DURATION_FORMAT_ERROR);
            }
        }
    }
}
