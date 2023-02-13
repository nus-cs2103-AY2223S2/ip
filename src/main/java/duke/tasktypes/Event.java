package duke.tasktypes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.ArrayList;

import duke.exceptions.DukeException;

/**
 * Represents an Event Task.
 * Event Task are unique in having a starting datetime and ending datetime.
 */
public class Event extends Task {

    private LocalDateTime endDateTime;
    private String endBy;

    private LocalDateTime startDateTime;
    private String startBy;

    private ArrayList<String> forSaving = new ArrayList<>();


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
        this.startDateTime = getDateTime(start);
        this.startBy = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT)
                        .format(this.startDateTime);

        this.endDateTime = getDateTime(end);
        this.endBy = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT)
                        .format(this.endDateTime);

        this.when = this.startDateTime;

    }
    private LocalDateTime getDateTime(String by) throws DukeException {
        try {
            String[] dateAndTime = by.split(" ");
            String date = dateAndTime[0];
            String time = dateAndTime[1];
            date = date.replace('/', '-');
            this.forSaving.add(date + " " + time);
            time = time.substring(0, 2) + ':' + time.substring(2);
            LocalDate byDate = LocalDate.parse(date);
            LocalTime byTime = LocalTime.parse(time);
            return LocalDateTime.of(byDate, byTime);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please enter your date and time in this format: yyyy/mm/dd HHMM");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Missing Date or Time input!");
        }
    }

    @Override
    public String getSaveFormat() {
        String done;
        if (this.isDone) {
            done = "1";
        } else {
            done = "0";
        }
        return "E" + ",," + done + ",," + this.description + ",,"
                + this.forSaving.get(0) + ",," + this.forSaving.get(1);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startBy + ", " + "to: " + this.endBy + ")";
    }
}
