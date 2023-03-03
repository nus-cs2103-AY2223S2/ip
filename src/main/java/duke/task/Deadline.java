package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.util.DukeException;

/**
 * Represents a Deadline that is a Task.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("EEEE, "
            + "MMMM d yyyy, h:mm a");
    private String doBy;
    private LocalDateTime deadline;

    /**
     * Constructor for Deadline
     * @param description Description of Deadline.
     * @param deadline Date of deadline.
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.doBy = deadline;
        this.deadline = LocalDateTime.parse(deadline);
    }

    /**
     * Translates the task into data format.
     * @return A String that represents the task in data format.
     */
    public String toData() {
        return String.format("D | %s | %s | %s", this.getStatusIcon(), this.getDescription(), this.doBy);
    }

    /**
     * Checks if Deadline details are valid.
     * @param deadlineDetails The details of Deadline task that are entered by user.
     * @return A String array containing the details that are required by Deadline.
     * @throws DukeException If there are missing details for Deadline.
     */
    public static String[] checkDeadlineDetails(String deadlineDetails) throws DukeException {
        if (deadlineDetails.isBlank()) {
            throw new DukeException("You did not enter the required details for your task!");
        }
        if (!deadlineDetails.contains("/by")) {
            throw new DukeException("You have entered a wrong format for this command :(\n"
                    + "Type 'help' for a list of commands and their respective formats.");
        }
        String deadlineDescription;
        try {
            deadlineDescription = deadlineDetails.split(" /by ", 2)[0];
            // If a description is not entered, index 1 will be empty.
            String deadline = deadlineDetails.split(" /by ", 2)[1];
            if (deadlineDescription.isBlank()) {
                throw new DukeException("You did not enter a description for your task!");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Please enter a description for your task!");
        }
        String deadline = deadlineDetails.split(" /by ", 2)[1];;
        if (deadline.isBlank()) {
            throw new DukeException("You did not enter a deadline for your task!");
        }
        return new String[] {deadlineDescription, deadline};
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (BY: %s)", this.getStatusIcon(),
                this.getDescription(), this.deadline.format(DATE_TIME_FORMATTER));
    }
}
