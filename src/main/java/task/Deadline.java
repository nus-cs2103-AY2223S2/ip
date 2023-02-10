package task;

import exception.CommandNotFoundException;
import exception.InvalidCommandInputException;
import exception.InvalidDateFormatException;

import helper.DateTimeHelper;

import java.time.LocalDateTime;

/**
 * Represents a deadline task that should be done by a given time.
 */
public class Deadline extends Task {

    private LocalDateTime deadline;

    /**
     * Constructor for deadline.
     * @param content Content to be done for the task.
     * @param deadlineString The time by which the task must be completed.
     * @throws InvalidDateFormatException If the time given for dateline is in the incorrect format.
     */
    Deadline(String content, String deadlineString) throws InvalidDateFormatException {
        super(content);
        this.deadline = DateTimeHelper.parse(deadlineString);
    }

    /**
     * Constructor for deadline.
     * @param content Content to be done for the task.
     * @param done Whether the task is completed.
     * @param deadlineString The time by which the task must be completed.
     * @throws InvalidDateFormatException If the time given for dateline is in the incorrect format.
     */
    Deadline(String content, boolean done, String deadlineString) throws InvalidDateFormatException {
        super(content, done);
        this.deadline = DateTimeHelper.parseFormattedDateTime(deadlineString);
    }

    /**
     * Checks if the deadline is the given datetime object.
     *
     * @param dt The given datetime object.
     * @return Whether the deadline is the given datetime object.
     */
    public boolean occursOn(LocalDateTime dt) {
        return dt.equals(deadline);
    }

    @Override
    public void update(String input) throws InvalidCommandInputException {

        if (!input.matches(".* /by .*")) {
            throw new InvalidCommandInputException("'/by' delimiter does not exist.", input);
        }

        String[] arr = input.split(" /by ");

        String content = arr[0];
        String deadline = arr[1];

        if (content.length() == 0 || deadline.length() == 0) {
            throw new InvalidCommandInputException("Empty argument", "deadline");
        }

        try {
            this.deadline = DateTimeHelper.parse(deadline);
            updateContent(content);
        } catch (InvalidDateFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + DateTimeHelper.stringify(deadline) + ")";
    }
}