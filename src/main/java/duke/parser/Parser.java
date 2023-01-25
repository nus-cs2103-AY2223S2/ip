package duke.parser;

import duke.enums.Commands;
import duke.enums.TaskTypes;
import duke.exceptions.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {

    private final int NUM_OF_DATES = 2;
    private int index;
    private String name;
    private LocalDate[] dates;

    public Parser() {
        this.dates = new LocalDate[NUM_OF_DATES];
    }

    public Commands parseInput(String input) throws DukeException {
        String[] delimited = input.split(" ");
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
            this.name = this.retrieveName(input, delimited);
            return Commands.TODO;
        case "deadline":
            this.name = this.retrieveName(input, delimited);
            this.retrieveDates(TaskTypes.DEADLINE, input, delimited);
            return Commands.DEADLINE;
        case "event":
            this.name = this.retrieveName(input, delimited);
            this.retrieveDates(TaskTypes.EVENT, input, delimited);
            return Commands.EVENT;
        case "delete":
            this.index = this.retrieveIndex(delimited);
            return Commands.DELETE;
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

    private String retrieveName(String input, String[] delimitedInput)
            throws DukeException {
        if (delimitedInput.length < 2) {
            throw new DukeException(
                    "Invalid description provided. "
                            + "The description of a task cannot be empty.");
        }
        return input.split(" /")[0].split(" ", 2)[1];
    }

    private void retrieveDates(TaskTypes type, String input, String[] delimitedInput)
            throws DukeException {
        String[] temp;
        switch (type) {
        case DEADLINE:
            if (!input.contains("/by")) {
                throw new DukeException("Please provide a deadline using /by");
            }
            temp = input.split(" /by ");
            if (temp.length < 2) {
                throw new DukeException("Please provide a valid deadline.");
            }
            try {
                this.dates[0] = LocalDate.parse(temp[1]);
            } catch (DateTimeParseException e) {
                throw new DukeException(
                        "Please provide the deadline in the following "
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
}
