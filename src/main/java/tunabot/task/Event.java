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
    public Event(String name, String start, String end) {
        super(name);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yy-HHmm");
        start = start.substring(0, start.length() - 1);
        this.start = LocalDateTime.parse(start, format);
        this.end = LocalDateTime.parse(end, format);
    }

    /**
     * Initializes new Event with given name, status, start date and end date
     * @param name Name of Event
     * @param isDone Status of Event
     * @param start Start date of Event
     * @param end End date of Event
     */
    public Event(String name, String isDone, String start, String end) {
        super(name, isDone);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy-HHmm");
        this.start = LocalDateTime.parse(start, formatter);
        this.end = LocalDateTime.parse(end, formatter);
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

    @Override
    public String saveFormat() {
        return "E;" + this.name + ";" + this.getDone() + ";" + this.getStart() + ";" + this.getEnd();
    }

    @Override
    public String toString() {
        String box;
        if (this.getDone()) {
            box = "[X] ";
        } else {
            box = "[ ] ";
        }
        return "[E]" + box + this.getName() + "(from: " + this.getStart() + " to " + this.getEnd() + ")";
    }
}
