package task;

import errors.DukeInsufficientArgumentException;
import errors.DukeInvalidCommandException;
import errors.DukeRuntimeException;
import formatters.Format;
import formatters.Response;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


/**
 * A Task with a deadline
 * @author Nicholas Lee
 */

public class Deadline extends Task{

    private final String deadline;

    public Deadline(String details, String deadline) {
        super(details);
        this.deadline = deadline;
    }


    /**
     * Prints out a formatted string containing the specific deadline details
     */
    @Override
    public String toString() {
        return ("DEADLINE: " + super.getDetails() +
                " (By " + deadline +")" + Format.getCompletionDisplay(super.isCompleted()));
    }


    /**
     * Returns parsed information specific to a "deadline" command
     *
     * Keys: "details" (the name of the deadline), "deadline" (the date of the deadline)
     * Throws a DukeInsufficientArgumentException if the input is unable to be parsed correctly
     *
     * @param input A deadline user command
     * @exception DukeInsufficientArgumentException if the user did not include any details with the "deadline" command
     * @exception DukeInvalidCommandException If the deadline details are out of order
     * @return A hashmap with keys "details" and "deadline"
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
