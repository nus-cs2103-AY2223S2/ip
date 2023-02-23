package duke;

import java.time.LocalDate;

import duke.exceptions.DukeyException;

/**
 * Analyzes user input.
 */
public class Parser {

    /**
     * Returns true if a given user input is empty.
     * @param input the user input
     */
    public boolean checkIfEmpty(String input) {
        return input.strip().equals("");
    }

    /**
     * Detects the user's command based on the user's input String, throws an exception if the user's input
     * does not match any of the existing commands.
     * @param input the user input
     * @return the detected action
     * @exception DukeyException on invalid user command
     */
    public ActionEnum parseCommand(String input) throws DukeyException {
        input = input.strip().toLowerCase();
        if (checkIfEmpty(input)) {
            throw new DukeyException("Error! Input cannot be empty.");
        }
        if (input.length() < 3) {
            throw new DukeyException("Invalid Command!");
        }
        if (input.substring(0,3).strip().equals("tod")) {
            return ActionEnum.TODO;
        }
        if (input.substring(0,3).strip().equals("dea")) {
            return ActionEnum.DEADLINE;
        }
        if (input.substring(0,3).strip().equals("eve")) {
            return ActionEnum.EVENT;
        }
        if (input.substring(0,3).strip().equals("lis")) {
            return ActionEnum.LIST;
        }
        if (input.substring(0,3).strip().equals("mar")) {
            return ActionEnum.MARK;
        }
        if (input.substring(0,3).strip().equals("unm")) {
            return ActionEnum.UNMARK;
        }
        if (input.substring(0,3).strip().equals("del")) {
            return ActionEnum.DELETE;
        }
        if (input.substring(0,3).strip().equals("sav")) {
            return ActionEnum.SAVE;
        }
        if (input.substring(0,3).strip().equals("cle")) {
            return ActionEnum.CLEARLIST;
        }
        if (input.substring(0,3).strip().equals("bye")) {
            return ActionEnum.BYE;
        }
        if (input.substring(0,3).strip().equals("for")) {
            return ActionEnum.FORCESTOP;
        }
        if (input.substring(0,3).strip().equals("fin")) {
            return ActionEnum.FIND;
        }

        throw new DukeyException("Error! Invalid command!");
    }


    /**
     * Scans the user's input for a Task name and returns the Task name, throws an exception if the
     * user input is empty.
     * @param input the user input
     * @throws DukeyException on empty input for Task name
     */
    public String parseTaskName(String input) throws DukeyException {
        input = input.strip();
        if (checkIfEmpty(input)) {
            throw new DukeyException("Error! Task name cannot be empty!");
        }
        return input;
    }

    /**
     * Scans the user's input for a date and returns the date in the form of a LocalDate. Throws an exception if the
     * user input is empty or if the date is in the incorrect format.
     * @param input the user input
     * @throws DukeyException on invalid string input for date
     */
    public LocalDate parseDate(String input) throws DukeyException {
        input = input.strip();
        if (checkIfEmpty(input)) {
            throw new DukeyException("Input cannot be empty!");
        }
        return DukeyTime.getDateFromString(input);
    }

    /**
     * Scans the user's input for a keywrod and returns the keyword. Throws an exception if the
     * user input is empty.
     * @param input the user input
     * @throws DukeyException on empty user input
     */
    public String parseKeyword(String input) throws DukeyException {
        input = input.strip();
        if (checkIfEmpty(input)) {
            throw new DukeyException("Input cannot be empty!");
        }
        return input;
    }
}
