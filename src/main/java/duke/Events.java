package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A task type that the chatting bot can create.
 */
public class Events extends Task {
    private LocalDate strDate;
    private LocalTime strTime;
    private LocalDate endDate;
    private LocalTime endTime;

    /**
     * The constructor of this class.
     *
     * @param name
     * @param strtime
     * @param endtime
     */
    public Events(String name, String strtime, String endtime) throws DukeException {
        super(name);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDateTime dateTime = LocalDateTime.parse(strtime, dtf);
            strDate = dateTime.toLocalDate();
            strTime = dateTime.toLocalTime();
        } catch (DateTimeParseException e) {
            try {
                LocalDate date = LocalDate.parse(strtime, df);
                this.strDate = date;
            } catch (DateTimeParseException e2) {
                throw new DukeException("The time format is invalid. Correct example: '/from 2023-02-16 /to 2023-02-17 18:00'");
            }
        }
        try {
            LocalDateTime dateTime = LocalDateTime.parse(endtime, dtf);
            endDate = dateTime.toLocalDate();
            endTime = dateTime.toLocalTime();
        } catch (DateTimeParseException e) {
            try {
                LocalDate date = LocalDate.parse(endtime, df);
                this.endDate = date;
            } catch (DateTimeParseException e2) {
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }

    }

    /**
     * The method that returns the starting time of the event.
     *
     * @return the starting time of the event.
     */
    public LocalTime getStrTime() {
        return this.strTime;
    }

    /**
     * The method that returns the ending time of the event.
     *
     * @return the ending time of the event.
     */
    public LocalTime getEndTime() {
        return this.endTime;
    }

    /**
     * The toString method.
     *
     * @return the task name with the specific times of the event and status.
     */
    public String toString() {
        DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm");
        DateTimeFormatter df = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String result = "";
        if (this.getStatus()) {
            result += "[D][X] " + this.getName() + " (from: ";
        } else {
            result += "[D][ ] " + this.getName() + " (from: ";
        }
        result += this.strDate.format(df);
        result += this.strTime == null ? "" : " " + this.strTime.format(tf);
        result += " to: ";
        result += this.endDate.format(df);
        result += this.endTime == null ? "" : " " + this.endTime.format(tf);
        result += ")";
        return result;
    }
}
