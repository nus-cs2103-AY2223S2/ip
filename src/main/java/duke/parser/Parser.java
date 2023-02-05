package duke.parser;

import duke.enums.Commands;
import duke.enums.TaskTypes;
import duke.exceptions.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

/**
 * This class manages the parsing of user inputs.
 */
public class Parser {

    private final int NUM_OF_DATES = 2;
    private int index;
    private String name;
    private LocalDate[] dates;
    private boolean awaitingConfirmation;

    public Parser() {
        this.dates = new LocalDate[NUM_OF_DATES];
        this.awaitingConfirmation = false;
    }

    /**
     * Parses through the user input to receive the command from
     * the user. Based on the command received, required
     * information would be retrieved from the user input. If an
     * invalid input was received, an exception is thrown.
     *
     * @param input The string containing user input.
     * @return An enum with the respective command from the user.
     * @throws DukeException If an invalid input was given.
     */
    public Commands parseInput(String input) throws DukeException {
        String[] delimited = input.split(" ");
        if (this.awaitingConfirmation) {
            switch(delimited[0].toLowerCase()) {
            case "yes":
            case "y":
                return Commands.YES;
            case "no":
            case "n":
                return Commands.NO;
            default:
                throw new DukeException("Please input either \"yes\" or \"no\".");
            }
        }
        switch (delimited[0].toLowerCase()) {
        case "bye":
            return Commands.BYE;
        case "list":
            return Commands.LIST;
        case "mark":
            this.index = this.retrieveIndex(delimited);
            return Commands.MARK;
        case "unmark":
            this.index = this.retrieveIndex(delimited);
            return Commands.UNMARK;
        case "todo":
            this.name = this.retrieveName(input);
            return Commands.TODO;
        case "deadline":
            this.name = this.retrieveName(input);
            this.retrieveDates(TaskTypes.DEADLINE, input);
            return Commands.DEADLINE;
        case "event":
            this.name = this.retrieveName(input);
            this.retrieveDates(TaskTypes.EVENT, input);
            return Commands.EVENT;
        case "delete":
            this.index = this.retrieveIndex(delimited);
            return Commands.DELETE;
        case "find":
            this.name = this.retrieveName(input);
            return Commands.FIND;
        default:
            return Commands.DEFAULT;
        }
    }

    private int retrieveIndex(String[] input) throws DukeException {
        if (input.length < 2) {
            throw new DukeException("Please provide a task number.");
        }
        try {
            int index = Integer.parseInt(input[1]);
            if (index < 1) {
                throw new DukeException("Invalid task number provided. "
                        + "Number cannot be less than 1.");
            }
            return index - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid task number provided.");
        }
    }

    private String retrieveName(String input)
            throws DukeException {
        try {
            return input.split(" /")[0].split(" ", 2)[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Invalid description provided.");
        }
    }

    private void retrieveDates(TaskTypes type, String input)
            throws DukeException {
        String[] temp;
        switch (type) {
        case DEADLINE:
            if (!input.contains("/by")) {
                throw new DukeException("Please provide a deadline using /by");
            }
            temp = input.split(" /by ");
            if (temp.length < 2) {
                throw new DukeException("Please provide a valid date.");
            }
            try {
                this.dates[0] = LocalDate.parse(temp[1]);
            } catch (DateTimeParseException e) {
                throw new DukeException(
                        "Please provide a valid date in the following "
                                + "format: YYYY-MM-DD.");
            }
            break;
        case EVENT:
            if (!input.contains("/from") || !input.contains("/to")) {
                throw new DukeException(
                        "Please provide a start date and end date "
                                + "using /from and /to respectively.");
            }
            temp = input.split(" /from ");
            String[] dates = temp[1].split(" /to ");
            if (dates.length < 2) {
                throw new DukeException("Please provide a valid start and end date.");
            }
            try {
                this.dates[0] = LocalDate.parse(dates[0]);
                this.dates[1] = LocalDate.parse(dates[1]);
            } catch (DateTimeParseException e) {
                throw new DukeException(
                        "Please provide the dates in the following "
                                + "format: YYYY-MM-DD.");
            }
            break;
        }
    }

    public int getIndex() {
        return this.index;
    }

    public String getName() {
        return this.name;
    }

    public LocalDate getDeadline() {
        return this.dates[0];
    }

    public LocalDate getStartDate() {
        return this.dates[0];
    }

    public LocalDate getEndDate() {
        return this.dates[1];
    }

    public void setAwaitingConfirmation(boolean bool) {
        this.awaitingConfirmation = bool;
    }
}
