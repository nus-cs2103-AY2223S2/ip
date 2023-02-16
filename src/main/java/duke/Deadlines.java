package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A task type that the chatting bot can create.
 */
public class Deadlines extends Task {

    private LocalDate date = null;
    private LocalTime time = null;

    /**
     * The constructor of this class.
     *
     * @param name
     * @param sTime
     */
    public Deadlines(String name, String sTime) throws DukeException {
        super(name);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDateTime dateTime = LocalDateTime.parse(sTime, dtf);
            date = dateTime.toLocalDate();
            time = dateTime.toLocalTime();
        } catch (DateTimeParseException e) {
            try {
                LocalDate date = LocalDate.parse(sTime, df);
                this.date = date;
            } catch (DateTimeParseException e2) {
                throw new DukeException("The time format is invalid. Correct examples: '/by 2023-02-16' and '/by 2023-02-16 18:00'");
            }
        }
    }

    /**
     * The method that returns the time of the deadline.
     *
     * @return the LocalTime variable of the deadline.
     */
    public LocalTime getTime() {
        return this.time;
    }

    /**
     * The method that returns the date of the deadline.
     *
     * @return the LocalDate variable of the deadline.
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * The toString method.
     *
     * @return the task name with the specific deadline and status.
     */
    public String toString() {
        DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm");
        DateTimeFormatter df = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String result = "";
        if (this.getStatus()) {
            result += "[D][X] " + this.getName() + " (by: ";
        } else {
            result += "[D][ ] " + this.getName() + " (by: ";
        }
        result += this.getDate().format(df);
        result += this.getTime() == null ? "" : " " + this.getTime().format(tf);
        result += ")";
        return result;
    }
}
