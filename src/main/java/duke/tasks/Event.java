package duke.tasks;

import com.sun.prism.shader.AlphaOne_Color_AlphaTest_Loader;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A class representing an Event task.
 */
public class Event extends Task {

    /** The starting date of this task. **/
    private LocalDateTime startDate;

    /** The ending date of this task. **/
    private LocalDateTime endDate;

    /**
     * Constructor for an Event task.
     * @param name Name of the task
     * @param startDate Starting date of the task
     * @param endDate Ending date of the task
     */
    public Event(String name, LocalDateTime startDate, LocalDateTime endDate) {
        super(name);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getStartDate() {
        return this.startDate.format(DateTimeFormatter.ofPattern("HHmm, MMM d yyyy"));
    }

    public String getEndDate() {
        return this.endDate.format(DateTimeFormatter.ofPattern("HHmm, MMM d yyyy"));
    }

    public boolean hasDateClash(LocalDateTime searchDate) {
        LocalDate newStartDate = this.startDate.toLocalDate();
        LocalDate newEndDate = this.endDate.toLocalDate();
        LocalDate newSearchDate = searchDate.toLocalDate();
        return !(newSearchDate.isBefore(newStartDate) || newSearchDate.isAfter(newEndDate));
    }

    public String toSaveFormat() {
        return String.format("E,%s,%s,%s,%s", this.name, this.getStatusIcon(), this.startDate, this.endDate);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (Starts: " + this.getStartDate() + ", ends: " + this.getEndDate() + ")";
    }
}
