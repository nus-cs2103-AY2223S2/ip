package Task;

import DukeException.DukeException;
import DukeException.TaskFormatException;
import duke.Times;

/**
 * Object class of Deadline
 * Deadline has and only has one time attribute
 */
public class Deadline extends Task {

    protected static final int DEADLINE = 8;
    protected static final int BY = 4; // /by/n = 4
    protected Times ddline;
    protected String type = "[D]";


    /**
     * Constructor for Deadline
     *
     * @param description -> Task description
     * @param by          -> String format of time deadline
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        ddline = new Times(by);
    }

    /**
     * Adds new deadline task.
     * Format: "deadline {description} /by {time}
     * example: deadline return book /by 2019-10-15 1530
     *
     * @param input Input Chat which starts with "deadline"
     * @throws DukeException if format of deadline task is wrong.
     */
    public static Deadline createDeadline(String input) throws DukeException {
        int ddlineIndex = input.indexOf("/");
        if (ddlineIndex == -1) {
            DukeException e = new TaskFormatException();
            throw e;
        }
        String description = input.substring(DEADLINE + 1, ddlineIndex - 1);
        String by = input.substring(ddlineIndex + BY);
        return new Deadline(description, by);
    }

    @Override
    public String toString() {
        return type + super.toString() + " (by: " + ddline + ")";
    }

    @Override
    public String getDescriptionAndTime() {
        return description + " (by: " + ddline + ")";
    }

}
