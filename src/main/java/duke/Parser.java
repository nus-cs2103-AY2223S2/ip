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

        input = input.substring(0,3);

        if (input.equals("tod")) {
            return ActionEnum.TODO;
        }
        if (input.equals("dea")) {
            return ActionEnum.DEADLINE;
        }
        if (input.equals("eve")) {
            return ActionEnum.EVENT;
        }
        if (input.equals("lis")) {
            return ActionEnum.LIST;
        }
        if (input.equals("mar")) {
            return ActionEnum.MARK;
        }
        if (input.equals("unm")) {
            return ActionEnum.UNMARK;
        }
        if (input.equals("del")) {
            return ActionEnum.DELETE;
        }
        if (input.equals("sav")) {
            return ActionEnum.SAVE;
        }
        if (input.equals("cle")) {
            return ActionEnum.CLEARLIST;
        }
        if (input.equals("bye")) {
            return ActionEnum.BYE;
        }
        if (input.equals("for")) {
            return ActionEnum.FORCESTOP;
        }
        if (input.equals("fin")) {
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
