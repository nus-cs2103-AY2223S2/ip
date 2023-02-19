package nook;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import exceptions.NookException;
import tasks.Priority;

/**
 * Represents the Validator which acts as a utility class
 * and handles all input validations
 */
public class Validator {
    private static final String DATE_ERROR_MESSAGE = "Woah.. Error parsing date time for deadline task. "
            + "Please enter the by datetime in this format yyyy-mm-dd (e.g., 2023-10-15).";

    /**
     * Returns a boolean indicating if the description is empty or not
     *
     * @param arr an array representation of the user's input line
     * @return if the description given by the user is empty
     */
    public boolean isDescriptionEmpty(String[] arr) {
        return arr.length == 1 || arr[1].trim().isEmpty();
    }

    /**
     * Returns a boolean indicating if the date is in the correct format to parse into a LocalDate
     *
     * @param input string that represents the date the user entered
     * @return if the date is in the correct format to parse into date
     */
    public boolean isDateValid(String input) {
        try {
            LocalDate.parse(input);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Returns a boolean indicating if the priority string parsed is a
     * valid and recognised priority
     *
     * @param input string that represents the priority the user entered
     * @return if the priority string parsed is a valid and recognised priority
     */
    public boolean isPriorityValid(String input) {
        Priority priority = Priority.getPriority(input);
        return priority != Priority.UNKNOWN;
    }

    /**
     * Generates the error message if task description is empty for a specified task
     *
     * @param task string of the task type derived from commandType
     * @return error message
     */
    private static String generateDescriptionErrorMessage(String task) {
        return "Hey now.. The description of a " + task + " cannot be empty. >:(";
    }

    /**
     * Validates the description string if it is empty
     * and throws a DukeException if it is empty
     *
     * @param inputArr an array representation of the user's input line
     * @param task     string of the task type derived from commandType
     * @throws NookException
     */
    public void validateDescription(String[] inputArr, String task) throws NookException {
        if (isDescriptionEmpty(inputArr)) {
            throw new NookException(generateDescriptionErrorMessage(task));
        }
    }

    /**
     * Validates the date string if it can be parsed into LocalDate
     * and throws a DukeException if it is unable to be parsed into LocalDate
     *
     * @param input string of the date to be parsed
     * @throws NookException
     */
    public void validateDate(String input) throws NookException {
        if (!isDateValid(input)) {
            throw new NookException(DATE_ERROR_MESSAGE);
        }
    }

    /**
     * Validates the priority string if it can be parsed into recognisable Priority
     * and throws a DukeException if it is unable to be parsed into recognisable Priority
     *
     * @param input string of the priority to be parsed
     * @throws NookException
     */
    public void validatePriority(String input) throws NookException {
        if (!isPriorityValid(input)) {
            throw new NookException("Oops! If you want to set a priority, "
                    + "please enter values such as: low, medium, high");
        }
    }
}
