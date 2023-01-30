package task;

import task.Task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
public class Event extends Task {

    protected String start;
    protected LocalTime startTime = LocalTime.of(0,0);
    protected String end;
    protected LocalTime endTime = LocalTime.of(23,59);

    public Event(String description, String start, String end, String remarks) {
        super(description, remarks);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM d yyyy");
        LocalDate s = LocalDate.parse(start);
        this.start = s.format(dtf);
        LocalDate e = LocalDate.parse(end);
        this.end = e.format(dtf);
    }

    public Event(String description, String start, String end, String startTime, String endTime, String remarks) {
        this(description, start, end, remarks);
        this.startTime = LocalTime.parse(startTime);
        this.endTime = LocalTime.parse(endTime);
    }


    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " " + startTime + " to: " + end + " " + endTime + ")";
    }
}