package duke.action;
import duke.data.TypeOfTask;
import duke.exception.DukeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class that tracks the start and end time for a defined task
 *
 * @author Haiqel Bin Hanaffi (Acerizm)
 */
public class Deadline extends Task {
    private LocalDate date;
    private LocalTime time;
    static private String DESIRED_DATE_FORMAT = "MMM dd yyyy";

    /**
     * Constructor that takes in the description of the task, date of the task and the time
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
     * @return cut-off date
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Returns the cut-off local time of the task
     * @return cut-off time
     */
    public LocalTime getTime() {
        return this.time;
    }

    /**
     * Prints the details of the task
     * @return details of tasks
     */
    @Override
    public String toString() {
        //return "[D]" + super.getDescription() + " (by: " + this.day + ")";
        String day = this.getDate().format(DateTimeFormatter.ofPattern(DESIRED_DATE_FORMAT));
        return String.format("[D][%s] %s (by: %s %s)", super.getStatusIcon(), super.getDescription(), day, this.getTime().toString());
    }
}
