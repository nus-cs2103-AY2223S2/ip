package task;

import java.time.LocalDate;
import java.time.Month;

/**
 * One of the three types of task a user can add.
 * Consist of 1 date element.
 */
public class Deadline extends Task {
    private LocalDate date;

    /**
     * Creates a new task of type deadline
     * @param instruction name user inputs for task
     * @param date string representation of date
     */
    public Deadline(String instruction, String date) {
        super(instruction.substring(9));
        this.date = LocalDate.parse(date);
    }

    @Override
    public String toString() {
        Month month = this.date.getMonth();
        int dayOfMonth = this.date.getDayOfMonth();
        int year = this.date.getYear();
        return "[D]" + super.toString() + " (by: " + month + " " + dayOfMonth + ", " + year + ")";
    }
}
