package duke.action;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import duke.data.TypeOfTask;
import duke.exception.DukeException;


/**
 * Represents deadlines that tracks the start and end time for a defined task
 *
 * @author Haiqel Bin Hanaffi (Acerizm)
 */
public class Deadline extends Task {
    private static final String desiredDateFormat = "MMM dd yyyy";
    private LocalDate date;
    private LocalTime time;
    /**
     * Represents default constructor that takes in the description of the task, date of the task and the time
     *
     * @param description Description of the task
     * @param date Date of task
     * @param time Cut-off time of task for completion
     * @throws DukeException if convertToLocalDate and convertToLocalTime fails to compute respectively
     */
    public Deadline(String description, String date, String time) throws DukeException {
        super(description, TypeOfTask.deadline);
        this.date = super.parser.covertToLocalDate(date);
        this.time = super.parser.convertToLocalTime(time);
    }

    /**
     * Returns the cut-off date for the task to be completed
     *
     * @return cut-off date
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Returns the cut-off local time of the task
     *
     * @return cut-off time
     */
    public LocalTime getTime() {
        return this.time;
    }

    /**
     * Prints the details of the task
     *
     * @return details of tasks
     */
    @Override
    public String toString() {
        String day = this.getDate().format(DateTimeFormatter.ofPattern(desiredDateFormat));
        return String.format("[D][%s] %s (by: %s %s)", super.getStatusIcon(),
                super.getDescription(), day, this.getTime().toString());
    }
}
