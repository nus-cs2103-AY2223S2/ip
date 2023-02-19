package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import exceptions.DukeException;

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
     * @param task string of the task type derived from commandType
     * @throws DukeException
     */
    public void validateDescription(String[] inputArr, String task) throws DukeException {
        if (isDescriptionEmpty(inputArr)) {
            throw new DukeException(generateDescriptionErrorMessage(task));
        }
    }

    /**
     * Validates the date string if it can be parsed into LocalDate
     * and throws a DukeException if it is unable to be parsed into LocalDate
     *
     * @param input string of the date to be parsed
     * @throws DukeException
     */
    public void validateDate(String input) throws DukeException {
        if (!isDateValid(input)) {
            throw new DukeException(DATE_ERROR_MESSAGE);
        }
    }
}
