package duke.tasktypes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

import duke.exceptions.DukeException;

/**
 * Represents a Deadline Task.
 * Deadline Tasks are unique in having an ending time.
 */
public class Deadline extends Task {

    private LocalDateTime byDateTime;
    private String forSaving;
    private String doneBy;

    /**
     * Constructs a Deadline Task.
     * Deadline Tasks are constructed with an ending time.
     *
     * @param description Description of task.
     * @param by Ending time of task.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        this.byDateTime = getByDateTime(by);
        this.doneBy = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT)
                        .format(this.byDateTime);
        this.when = this.byDateTime;
    }

    private LocalDateTime getByDateTime(String by) throws DukeException {
        try {
            String[] dateAndTime = by.split(" ");
            String date = dateAndTime[0];
            String time = dateAndTime[1];
            date = date.replace('/', '-');
            this.forSaving = date + " " + time;
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
        return "D" + ",," + done + ",," + this.description + ",," + this.forSaving;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.doneBy + ")";
    }
}


