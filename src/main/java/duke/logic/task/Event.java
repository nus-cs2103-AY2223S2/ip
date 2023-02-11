package duke.logic.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.DukeException;

/**
 * Event task with start time and end time.
 */
public class Event extends Task {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    protected LocalDateTime startTime;
    protected LocalDateTime endTime;

    /**
     * Constructor for Event object.
     *
     * @param description Description of task.
     * @param startTime Starting time of Event in the format yyyy-MM-dd HH:mm.
     * @param endTime Ending time of Event in the format yyyy-MM-dd HH:mm.
     */
    public Event(String description, LocalDateTime startTime, LocalDateTime endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Constructor for Event object with boolean input.
     *
     * @param description Description of task.
     * @param startTime Starting time of Event in the format yyyy-MM-dd HH:mm.
     * @param endTime Ending time of Event in the format yyyy-MM-dd HH:mm.
     * @param isDone Whether task is marked complete.
     */
    public Event(String description, LocalDateTime startTime, LocalDateTime endTime, Boolean isDone) {
        super(description, isDone);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Factory method to create Event object. String object should contain
     * startTime and endTime in the format /from yyyy-MM-dd HH:mm /to yyyy-MM-dd HH:mm.
     *
     * @param str String to be processed into Event object.
     * @return Event object.
     * @throws DukeException If format of input is incorrect.
     */
    public static Event create(String str) throws DukeException {
        if (str.length() < 1) {
            throw new DukeException();
        } else {
            String[] text = str.substring(1).split(" /");
            if (text.length < 3) {
                throw new DukeException();
            } else {
                String desc = text[0];
                String from = text[1].substring(5);
                String to = text[2].substring(3);
                return new Event(desc, LocalDateTime.parse(from, Event.FORMATTER),
                        LocalDateTime.parse(to, Event.FORMATTER));
            }
        }
    }

    /**
     * Factory method to create Event object with boolean input. String object should contain
     * startTime and endTime in the format /from yyyy-MM-dd HH:mm /to yyyy-MM-dd HH:mm.
     *
     * @param str String to be processed into Event object.
     * @param isDone Whether Event object should be mark as completed.
     * @return Event object.
     * @throws DukeException If format of input is incorrect.
     */
    public static Event create(String str, Boolean isDone) throws DukeException {
        if (str.length() < 1) {
            throw new DukeException();
        } else {
            String[] text = str.substring(1).split(" /");
            if (text.length < 3) {
                throw new DukeException();
            } else {
                String desc = text[0];
                String from = text[1].substring(5);
                String to = text[2].substring(3);
                return new Event(desc, LocalDateTime.parse(from, Event.FORMATTER),
                        LocalDateTime.parse(to, Event.FORMATTER), isDone);
            }
        }
    }

    public String getStartTime() {
        return this.startTime.format(FORMATTER);
    }

    public String getEndTime() {
        return this.endTime.format(FORMATTER);
    }

    public LocalDate getLocalDateStart() {
        return this.startTime.toLocalDate();
    }

    public LocalDate getLocalDateEnd() {
        return this.endTime.toLocalDate();
    }

    @Override
    public String getType() {
        return "event";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: "
                + this.startTime.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm"))
                + " to: "
                + this.endTime.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm"))
                + ")";
    }
}
