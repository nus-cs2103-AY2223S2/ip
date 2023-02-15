package duke.task;
import duke.exceptions.IncorrectDateFormatException;
import duke.exceptions.NeroException;
import duke.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Event is a subclass of Task. It specifies the start date and
 * end date of a task.
 */
public class Event extends Task {

    private LocalDate startDate;
    private LocalDate endDate;


    /**
     * Constructor for Event
     * @param description Contains details on the Event
     * @param isDone Keeps track of whether the Event is completed
     * @param startDate Date where the Event starts
     * @param endDate Date where the Event ends
     * @throws NeroException Thrown when date is formatted incorrectly (not in yyyy-mm-dd)
     */
    public Event(String description, boolean isDone,
                 String startDate, String endDate) throws NeroException {
        super(description, isDone);
        try {
            this.startDate = LocalDate.parse(startDate.trim());
            this.endDate = LocalDate.parse(endDate.trim());
            assert this.endDate.isAfter(this.startDate);
        } catch (DateTimeParseException e) {
            throw new IncorrectDateFormatException();
        } catch (AssertionError e) {
            throw new NeroException("End date must be after start date!");
        }
    }

    /**
     * Same as above constructor, except isDone is initialised to False
     * @param description
     * @param startDate
     * @param endDate
     * @throws NeroException
     */
    public Event(String description, String startDate,
            String endDate) throws NeroException {
        this(description, false, startDate, endDate);
    }

    /**
     * @return String formatted as d MMM yyyy
     */
    public String dateFormatter() {
        return "from: " + startDate.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + " to: "
                + endDate.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
    }


    public String getTaskIcon() {
        return "E";
    }

    @Override
    /**
     * @return String with task formatted to be saved into duke.txt
     */
    public String toSave() {
        return this.getTaskIcon() + SEPARATOR + convertBoolean()
                + SEPARATOR + this.getDescription()
                + SEPARATOR + this.startDate + " " + this.endDate;
    }
    @Override
    public String toString() {
        return String.format("[%s]%s %s %s", this.getTaskIcon(),
                this.getStatusIcon(), this.getDescription(),
                this.dateFormatter());
    }

}
