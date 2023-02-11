package tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected String start;
    protected String end;
    protected LocalDate startDate;
    protected LocalDate endDate;
    protected LocalTime startTime = null;
    protected LocalTime endTime = null;
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public void handleDateAndTime() {
        if (start.contains(" ")) {
            LocalDate newStartDate = LocalDate.parse(start.split(" ")[0]);
            LocalTime newStartTime = LocalTime.parse(start.split(" ")[1]);
            this.startDate = newStartDate;
            this.startTime = newStartTime;
        }
        if (end.contains(" ")) {
            LocalDate newEndDate = LocalDate.parse(end.split(" ")[0]);
            LocalTime newEndTime = LocalTime.parse(end.split(" ")[1]);
            this.endDate = newEndDate;
            this.endTime = newEndTime;
        }
        else {
            LocalDate newStartDate = LocalDate.parse(start);
            LocalDate newEndDate = LocalDate.parse(end);
            this.startDate = newStartDate;
            this.endDate = newEndDate;
        }
    }

    public String printStartDateAndTime() {
        if (startTime.equals(null)) {
            return startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }
        else {
            return startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + Integer.toString(startTime.getHour()) + Integer.toString(startTime.getMinute());
        }
    }

    public String printEndDateAndTime() {
        if (endTime.equals(null)) {
            return endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }
        else {
            return endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + Integer.toString(endTime.getHour()) + Integer.toString(endTime.getMinute());
        }
    }


    @Override
    public String toString() {
        return "[E]" + "[" + this.getStatusIcon() + "] " + this.description + " (from: " + printStartDateAndTime() + " to: " + printEndDateAndTime() + ")";
    }
}
