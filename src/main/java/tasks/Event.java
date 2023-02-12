package tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected String start;
    protected String end;
    protected LocalDate startDate;
    protected LocalDate endDate;
    protected LocalTime startTime;
    protected LocalTime endTime;
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
        handleDateAndTime();
    }

    public void handleDateAndTime() {
        if (start.contains(" ")) {
            LocalDate newStartDate = LocalDate.parse(start.split(" ")[0]);
            LocalTime newStartTime = LocalTime.parse(start.split(" ")[1]);
            this.startDate = newStartDate;
            this.startTime = newStartTime;
        } else {
            LocalDate newStartDate = LocalDate.parse(start);
            this.startDate = newStartDate;
        }

        if (end.contains(" ")) {
            LocalDate newEndDate = LocalDate.parse(end.split(" ")[0]);
            LocalTime newEndTime = LocalTime.parse(end.split(" ")[1]);
            this.endDate = newEndDate;
            this.endTime = newEndTime;
        } else {
            LocalDate newEndDate = LocalDate.parse(end);
            this.endDate = newEndDate;
        }
    }

    public String printStartDateAndTime() {
        if (!start.contains(" ")) {
            return startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }
        else {
            return startDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ", " + startTime.getHour() + startTime.getMinute();
        }
    }

    public String printEndDateAndTime() {
        if (!end.contains(" ")) {
            return endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }
        else {
            return endDate.format(DateTimeFormatter.ofPattern("MMM dd  yyyy")) + ", " + endTime.getHour() + endTime.getMinute();
        }
    }

    @Override
    public String toString() {
        return "[E]" + "[" + this.getStatusIcon() + "] " + this.getDescription() + " (from: " + printStartDateAndTime() + " to: " + printEndDateAndTime() + ")";
    }

    @Override
    public String writeToFile() {
        return "[E]" + "[" + this.getStatusIcon() + "] " + this.getDescription() + " (from: " + start + " to: " + end + ")";
    }
}
