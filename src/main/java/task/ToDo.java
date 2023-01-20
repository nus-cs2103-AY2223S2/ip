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
 * A generic Task-like event without any additional details
 * @author Nicholas Lee
 */

public class ToDo extends Task{

    public ToDo(String details) {
        super(details);
    }

    @Override
    public String toString() {
        return ("TO-DO: " + super.getDetails() + Format.getCompletionDisplay(super.isCompleted()));
    }

    /**
     * Returns a hashmap containing parsed information specific to a "to-do" command
     *
     * Keys: "details" (the name of the to-do)
     * Throws a DukeInsufficientArgumentException if the input is unable to be parsed correctly
     *
     * @param input A to-do user command
     * @exception DukeInsufficientArgumentException If the to-do details are out of order
     * or the user did not include any details with the "to-do" command
     */
    public static HashMap<String, String> parse(String input) throws DukeRuntimeException {

        List<String> segments = Arrays.asList(input.split(" "));
        List<String> detailsSublist;

        int detailsIndex = segments.indexOf("to-do") + 1;

        if (segments.size() <= 1) {
            throw new DukeInsufficientArgumentException(Response.MISSING_TASK_NAME.toString());
        }

        try {
            detailsSublist = segments.subList(detailsIndex, segments.size());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidCommandException(Response.INVALID_COMMAND.toString());
        }

        String details = String.join(" ", detailsSublist);

        HashMap<String, String> result = new HashMap<>();
        result.put("details", details);
        return result;
    }
}
