package brotherbot.storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime deadline;

    /**
     * Constructor to create a Deadline object.
     *
     * @param description Description of the task.
     * @param deadline Deadline of the task.
     */
    public Deadline(String description, String deadline) {
        super(description);
        super.type = "D";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        this.deadline = LocalDateTime.parse(deadline, formatter);
    }

    /**
     * Prints type, status, description and deadline of Task.
     *
     * @return String representation of Deadline object.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        return  "[" + super.type + "] "  + "[" + this.getStatusIcon() + "] " + this.description + " By: " + this.deadline.format(formatter);
    }

}
