package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline task that stores the description of the task
 * as well as a date deadline that gets reformatted.
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Creates a Deadline Task
     *
     * @param description in String format.
     * @param by Which is a LocalDate.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Formats the LocalDate deadline into 23/05/2019 format.
     *
     * @return String output for listing the tasks
     */
    @Override
    public String toString() {
        String formattedDate = this.by.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return "[D]" + super.toString() + " (by: " + formattedDate + ")";
    }

    /**
     * Formats the LocalDate deadline into 23-05-2019 format.
     *
     * @return String output for storing in the  hard disk text file.
     */
    @Override
    public String toFileString() {
        String formattedDate = this.by.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        return "D | " + super.toFileString() + " | " + formattedDate + "\n";
    }
}

