package hachi.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates a Task with a deadline.
 */
public class Deadline extends Task {
    private String time;

    /**
     * Ddl constructor.
     *
     * @param input The description of the task.
     * @param time  The deadline of the task.
     */
    public Deadline(String input, String time) {
        super(input);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate output = LocalDate.parse(time, dateFormatter);
        DateTimeFormatter newPattern = DateTimeFormatter.ofPattern("MMM dd yyyy");
        this.time = output.format(newPattern);

    }


    /**
     * Returns the string representation of the Ddl object with
     * specified description and date.
     *
     * @return String representation of the Ddl object
     */
    public String toString() {
        return "   [D]" + super.toString() + " (by: " + this.time + ")";
    }

}
