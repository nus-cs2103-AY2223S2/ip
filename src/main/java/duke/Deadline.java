package duke;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;


/**
 * Encapsulates a Deadline as a specific type of Task.
 */
public class Deadline extends Task {
    private LocalDate deadline;

    /**
     * Creates a Deadline object.
     * @param description The description of the Deadline task.
     * @param deadline The deadline of the task to be input in DD/MM/YYYY format.
     */
    Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Creates a string representation of the Deadline object to be input in the list.
     * @return The string representation of the deadline with the description and date.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    /**
     * Creates a string representation of the Deadline task that is saved in a file.
     * @return The string representation of the Deadline task that is stored in a text file.
     */
    @Override
    public String sendOutputToFile() {
        return String.format("D | %d | %s | %s", isDone ? 1 : 0, description,
                deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
