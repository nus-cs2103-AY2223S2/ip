package tunabot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import tunabot.exceptions.InputException;

/**
 * Class to handle Events
 */
public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;

    /**
     * Initializes new Event with given name, start date and end date
     * @param name Name of Event
     * @param start Start date of Event
     * @param end End date of Event
     */
    public Event(String name, String start, String end) throws InputException {
        super(name);
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yy-HHmm");
            this.start = LocalDateTime.parse(start, format);
            this.end = LocalDateTime.parse(end, format);
        } catch (DateTimeParseException e) {
            throw new InputException("BLUB! Date format is invalid! It should be dd/mm/yy-hhmm.");
        }
    }

    /**
     * Initializes new Event with given name, status, start date and end date
     * @param name Name of Event
     * @param isDone Status of Event
     * @param start Start date of Event
     * @param end End date of Event
     */
    public Event(String name, String isDone, String start, String end) throws InputException {
        super(name, isDone);
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yy-HHmm");
            this.start = LocalDateTime.parse(start, format);
            this.end = LocalDateTime.parse(end, format);
        } catch (DateTimeParseException e) {
            throw new InputException("BLUB! Date format is invalid! It should be dd/mm/yy-hhmm.");
        }
    }

    public void setEnd(String end) throws InputException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy-HHmm");
        try {
            this.end = LocalDateTime.parse(end, formatter);
        } catch (DateTimeParseException e) {
            throw new InputException("BLUB! Date format is invalid! It should be dd/mm/yy-hhmm.");
        }
    }
    public String getEnd() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy-HHmm");
        return end.format(formatter);
    }
    public void setStart(String start) throws InputException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy-HHmm");
        try {
            this.start = LocalDateTime.parse(start, formatter);
        } catch (DateTimeParseException e) {
            throw new InputException("BLUB! Date format is invalid! It should be dd/mm/yy-hhmm.");
        }
    }
    public String getStart() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy-HHmm");
        return start.format(formatter);
    }

    /**
     * Returns a string representation of the event that matches the format used in the save file
     * @return save file friendly format
     */
    @Override
    public String saveFormat() {
        return "E;" + this.getName() + ";" + this.getDone() + ";" + this.getStart() + ";" + this.getEnd();
    }

    @Override
    public String toString() {
        String box;
        if (this.getDone()) {
            box = "[X] ";
        } else {
            box = "[ ] ";
        }
        return "[E]" + box + this.getName() + " (from: " + this.getStart() + " to " + this.getEnd() + ")";
    }
}
