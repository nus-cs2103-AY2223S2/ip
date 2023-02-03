package duke.tasktypes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

import duke.exceptions.DukeException;

/**
 * Represents an Event Task.
 * Event Task are unique in having a starting datetime and ending datetime.
 */
public class Event extends Task {

    private LocalDate endDate;
    private LocalTime endTime;
    private LocalDateTime endDateTime;
    private String endBy;

    private LocalDate startDate;
    private LocalTime startTime;
    private LocalDateTime startDateTime;
    private String startBy;

    private String forSavingStart;
    private String forSavingEnd;

    /**
     * Constructs an Event Task instance.
     * Event tasks are constructed with a starting and ending time.
     *
     * @param description Description of task.
     * @param start Starting time of Event.
     * @param end Ending time of Event
     */
    public Event(String description, String start, String end) throws DukeException {
        super(description);
        String[] startDateAndTime = start.split(" ");
        String startDate = startDateAndTime[0];
        String startTime = startDateAndTime[1];
        startDate = startDate.replace('/', '-');
        this.forSavingStart = startDate + " " + startTime;
        startTime = startTime.substring(0, 2) + ':' + startTime.substring(2);

        try {
            this.startDate = LocalDate.parse(startDate);
            this.startTime = LocalTime.parse(startTime);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please enter your date and time in this format: yyyy/mm/dd HHMM");
        }
        this.startDateTime = LocalDateTime.of(this.startDate, this.startTime);
        this.startBy = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT)
                        .format(this.startDateTime);


        String[] endDateAndTime = end.split(" ");
        String endDate = endDateAndTime[0];
        String endTime = endDateAndTime[1];
        endDate = endDate.replace('/', '-');
        this.forSavingEnd = endDate + " " + endTime;
        endTime = endTime.substring(0, 2) + ':' + endTime.substring(2);

        try {
            this.endDate = LocalDate.parse(endDate);
            this.endTime = LocalTime.parse(endTime);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please enter your date and time in this format: yyyy/mm/dd HHMM");
        }
        this.endDateTime = LocalDateTime.of(this.endDate, this.endTime);
        this.endBy = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT)
                        .format(this.endDateTime);

    }

    @Override
    public String getSaveFormat() {
        String done;
        if (this.isDone) {
            done = "1";
        } else {
            done = "0";
        }
        return "E" + ",," + done + ",," + this.description + ",," + this.forSavingStart + ",," + this.forSavingEnd;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startBy + ", " + "to: " + this.endBy + ")";
    }
}
