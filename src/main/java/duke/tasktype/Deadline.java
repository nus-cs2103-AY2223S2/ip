package duke.tasktype;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The class for Deadline tasks.
 */
public class Deadline extends Task {
    protected String time;

    /**
     * The constructor for Deadline class.
     *
     * @param cont the content of the task
     * @param time the deadline of the task
     */
    public Deadline(String cont, String time) {
        super(cont);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("MM dd yyyy HH:mm");

        DateTimeFormatter dateOnlyFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter dateOnlyFormatter1 = DateTimeFormatter.ofPattern("MM dd yyyy");

        try {
            LocalDateTime dateTime = LocalDateTime.parse(time, dateTimeFormatter);
            this.time = dateTime.format(dateTimeFormatter1);
        } catch (DateTimeParseException e) {
            try {
                LocalDate dateOnly = LocalDate.parse(time, dateOnlyFormatter);
                this.time = dateOnly.format(dateOnlyFormatter1);
            } catch (DateTimeParseException e1) {
                this.time = time;
            }
        }
    }

    /**
     * Override the toString() method to show the information of the Deadline task.
     *
     * @return a String that shows the information of the Deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.time + ")";
    }
}
