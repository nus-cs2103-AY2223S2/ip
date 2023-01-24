package tasktypes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Event extends Task {
    LocalDate endDate;
    LocalTime endTime;
    LocalDateTime endDateTime;
    String endBy;

    LocalDate startDate;
    LocalTime startTime;
    LocalDateTime startDateTime;
    String startBy;

    public Event(String description, String start, String end) {
        super(description);
        String[] startDateAndTime = start.split(" ");
        String startDate = startDateAndTime[0];
        String startTime = startDateAndTime[1];
        startDate = startDate.replace('/','-');
        startTime = startTime.substring(0, 2) + ':' + startTime.substring(2);

        this.startDate = LocalDate.parse(startDate);
        this.startTime = LocalTime.parse(startTime);
        this.startDateTime = LocalDateTime.of(this.startDate, this.startTime);
        this.startBy = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT).
                format(this.startDateTime);

        String[] endDateAndTime = end.split(" ");
        String endDate = endDateAndTime[0];
        String endTime = endDateAndTime[1];
        endDate = endDate.replace('/','-');
        endTime = endTime.substring(0, 2) + ':' + endTime.substring(2);

        this.endDate = LocalDate.parse(endDate);
        this.endTime = LocalTime.parse(endTime);
        this.endDateTime = LocalDateTime.of(this.endDate, this.endTime);
        this.endBy = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT).
                format(this.endDateTime);

        Task.numTask++;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startBy + ", " + "to: " + this.endBy + ")";
    }
}