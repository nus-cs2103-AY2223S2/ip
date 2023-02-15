package nemo.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import nemo.exception.NemoException;

/**
 * Represents Deadline task with due by date.
 *
 * @author Lian Kok Hai
 */
public class Deadline extends Task {
    private LocalDate by;

    /**
     * Constructs Deadline object.
     *
     * @param taskName Name of Deadline task.
     * @param by Due date of task.
     * @throws NemoException Thrown when format of date not recognized.
     */
    public Deadline(String taskName, String by) throws NemoException {
        super(taskName);
        this.type = "D";
        try {
            LocalDate byDate = LocalDate.parse(by);
            this.by = byDate;
        } catch (DateTimeParseException e) {
            throw new NemoException("The format of date was not recognized. Try something like this YYYY-MM-DD. :)");
        }
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }

    @Override
    public String encode() {
        return String.format("%s | %s | %s | %s", this.type, this.isDone, this.taskName, this.by);
    }
}
