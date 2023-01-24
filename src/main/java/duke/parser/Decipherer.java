package duke.parser;

import duke.command.*;
import duke.exception.InvalidInputException;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.TodoTask;

import java.time.LocalDate;
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

    /**
     * Decodes the information string for an update command.
     * The input string should be in the format of "index description".
     *
     * @param information the string to be decoded
     * @return an UpdateCommand object
     * @throws InvalidInputException if the input task index is not a number or the description is empty
     */
    public static UpdateCommand updateDecoder(String information) throws InvalidInputException {
        // Split the input string into two parts: the index and the description
        String[] parts = information.split(" ", 2);
        // Check if the input string has enough parts
        if (parts.length < 2) {
            throw new InvalidInputException("OOPS!!! The description cannot be empty.");
        }
        try {
            // Try to convert the first part to an integer and decrement by 1
            int index = Integer.parseInt(parts[0]) - 1;
            return new UpdateCommand(index, parts[1]);
        } catch (NumberFormatException e) {
            // If the first part is not a number, throw an exception
            throw new InvalidInputException("OOPS!!! The input task index is not a number,\n"
                    + "Please input a valid task index");
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
    public static MarkAsDoneCommand markDecoder(String information) throws InvalidInputException {
        // Use regular expression to check if the input is a number
        Matcher numberChecker = Pattern.compile("\\d+?").matcher(information);
        if (numberChecker.matches()) {
            // convert the input to an integer and decrement by 1
            return new MarkAsDoneCommand(Integer.parseInt(information) - 1);
        } else {
            // if the input is not a number, throw an exception
            throw new InvalidInputException("OOPS!!! The input task index is not a number,\n"
                    + "Please input a valid task index");
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
    public static UnmarkCommand unmarkDecoder(String information) throws InvalidInputException {
        // Use regular expression to check if the input is a number
        Matcher numberChecker = Pattern.compile("\\d+?").matcher(information);
        if (numberChecker.matches()) {
            // convert the input to an integer and decrement by 1
            return new UnmarkCommand(Integer.parseInt(information) - 1);
        } else {
            // if the input is not a number, throw an exception
            throw new InvalidInputException("OOPS!!! The input task index is not a number,\n"
                    + "Please input a valid task index");
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
    public static DeleteCommand deleteDecoder(String information) throws InvalidInputException {
        // Use regular expression to check if the input is a number
        Matcher numberChecker = Pattern.compile("\\d+?").matcher(information);
        if (numberChecker.matches()) {
            // convert the input to an integer and decrement by 1
            return new DeleteCommand(Integer.parseInt(information) - 1);
        } else {
            // if the input is not a number, throw an exception
            throw new InvalidInputException("OOPS!!! The input task index is not a number,\n"
                    + "Please input a valid task index");
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
    public static AddTaskCommand todoDecoder(String information) throws InvalidInputException {
        // check if the input is not empty
        if (!emptyStringChecker.matcher(information).matches()) {
            throw new InvalidInputException("OOPS!!! The description of a todo cannot be empty.");
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
    public static AddTaskCommand deadlineDecoder(String information) throws InvalidInputException {
        // check if the input is not empty
        if (!emptyStringChecker.matcher(information).matches()) {
            throw new InvalidInputException("OOPS!!! The description of a deadline cannot be empty.");
        } else {
            // Use regular expression to extract the name and date
            Matcher dateChecker = Pattern.compile("(?<name>.*)/by(?<date>.*)").matcher(information);
            if (dateChecker.matches()) {
                String name = dateChecker.group("name").strip();
                String date = dateChecker.group("date").strip();
                try {
                    // create a new DeadlineTask and return an AddTaskCommand with it
                    return new AddTaskCommand(new DeadlineTask(name, LocalDate.parse(date)));
                } catch (DateTimeParseException e) {
                    // if the input date format is invalid, throw an exception
                    throw new InvalidInputException("OOPS!!! The input date format is invalid\n"
                            + "Please input the date in the format of yyyy-mm-dd");
                }
            } else {
                // if the input format is incorrect, throw an exception
                throw new InvalidInputException("OOPS!!! Please input the deadline in the correct format.");
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
    public static AddTaskCommand eventDecoder(String information) throws InvalidInputException {
        // check if the input is not empty
        if (!emptyStringChecker.matcher(information).matches()) {
            throw new InvalidInputException("OOPS!!! The description of an event cannot be empty.");
        } else {
            // Use regular expression to extract the name, start date, and end date
            Matcher intervalChecker = Pattern.compile("(?<name>.*)/from(?<from>.*)/to(?<to>.*)")
                    .matcher(information);
            if (intervalChecker.matches()) {
                String name = intervalChecker.group("name").strip();
                String from = intervalChecker.group("from").strip();
                String to = intervalChecker.group("to").strip();
                try {
                    // create a new EventTask and return an AddTaskCommand with it
                    return new AddTaskCommand(
                            new EventTask(name, LocalDate.parse(from), LocalDate.parse(to)));
                } catch (DateTimeParseException e) {
                    // if the input date format is invalid, throw an exception
                    throw new InvalidInputException("OOPS!!! The input date format is invalid\n"
                            + "Please input the date in the format of yyyy-mm-dd");
                }
            } else {
                // if the input format is incorrect, throw an exception
                throw new InvalidInputException("OOPS!!! Please input the event in the correct format.");
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
    public static FindCommand findDecoder(String information) throws InvalidInputException {
        // check if the input is not empty
        if (!emptyStringChecker.matcher(information).matches()) {
            throw new InvalidInputException("OOPS!!! The description of a todo cannot be empty.");
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
     * @return a SearchCommand object
     * @throws InvalidInputException if the input task description is empty,
     * or the input date format is invalid
     */
    public static SearchCommand searchDecoder(String information) throws InvalidInputException {
        // check if the input is not empty
        if (!emptyStringChecker.matcher(information).matches()) {
            throw new InvalidInputException("OOPS!!! The description of a todo cannot be empty.");
        } else {
            try {
                // create a new SearchCommand with the date
                return new SearchCommand(LocalDate.parse(information));
            } catch (DateTimeParseException e) {
                // if the input date format is invalid, throw an exception
                throw new InvalidInputException("OOPS!!! The input date format is invalid\n"
                        + "Please input the date in the format of yyyy-mm-dd");
            }
        }
    }
}
