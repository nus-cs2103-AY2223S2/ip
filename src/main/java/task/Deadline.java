package task;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import errors.DukeInsufficientArgumentException;
import errors.DukeInvalidCommandException;
import errors.DukeRuntimeException;
import ui.Format;
import ui.Response;
import utils.Utility;



/**
 * A Task with a deadline
 * @author Nicholas Lee
 */
public class Deadline extends Task {

    private final LocalDateTime deadline;

    /**
     * Creates a new Deadline object with the given details and deadline time.
     *
     * @param details a string describing the task associated with the deadline
     * @param deadline the LocalDateTime object representing the deadline of the task
     */
    public Deadline(String details, LocalDateTime deadline) {
        super(details);
        this.deadline = deadline;
    }

    /**
     * Creates a new Deadline object with the given details and deadline time.
     *
     * @param details a string describing the task associated with the deadline
     * @param deadline the LocalDateTime object representing the deadline of the task
     * @param isCompleted a boolean indicating whether the task has been completed
     */
    public Deadline(String details, LocalDateTime deadline, boolean isCompleted) {
        super(details);
        this.deadline = deadline;
        super.changeStatus(isCompleted);
    }

    /**
     * Prints out a formatted string containing the specific deadline details
     */
    @Override
    public String toString() {
        return ("DEADLINE: " + super.getDetails()
                + " (By " + Utility.getDateTimeString(deadline) + ")"
                + Format.getCompletionDisplay(super.isCompleted()));
    }

    /**
     * Returns the value of the private field 'deadline' for storage as a String.
     *
     * @return The current value of 'deadline' formatted as a String.
     */
    public String getDeadline() {
        return Utility.convertDateTimeToString(this.deadline);
    }


    /**
     * Returns parsed information specific to a "deadline" command
     *
     * Keys: "details" (the name of the deadline), "deadline" (the date of the deadline)
     * Throws a DukeInsufficientArgumentException if the input is unable to be parsed correctly
     *
     * @param input A deadline user command
     * @return A hashmap with keys "details" and "deadline"
     * @exception DukeInsufficientArgumentException if the user did not include any details with the "deadline" command
     * @exception DukeInvalidCommandException If the deadline details are out of order
     */
    public static HashMap<String, String> parse(String input) throws DukeRuntimeException {

        List<String> segments = Arrays.asList(input.split(" "));
        List<String> detailsSublist;
        List<String> deadlineSublist;

        int detailsIndex = segments.indexOf("deadline") + 1;
        int deadlineIndex = segments.indexOf("/by") + 1;

        if (segments.size() <= 1 || detailsIndex == 0 || deadlineIndex == 0) {
            throw new DukeInsufficientArgumentException(Response.MISSING_DEADLINE_DETAILS.toString());
        }

        try {
            detailsSublist = segments.subList(detailsIndex, deadlineIndex - 1);
            deadlineSublist = segments.subList(deadlineIndex, segments.size());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidCommandException(Response.INVALID_COMMAND.toString());
        }

        if (detailsSublist.isEmpty() || deadlineSublist.isEmpty()) {
            throw new DukeInsufficientArgumentException(Response.MISSING_DEADLINE_DETAILS.toString());
        }

        String details = String.join(" ", detailsSublist);
        String deadline = String.join(" ", deadlineSublist);

        HashMap<String, String> result = new HashMap<>();
        result.put("details", details);
        result.put("deadline", deadline);
        return result;
    }
}
