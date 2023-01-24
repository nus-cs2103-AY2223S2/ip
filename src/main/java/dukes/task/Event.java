package dukes.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Event extends Task {
    LocalDate start;
    LocalDate end;

    public Event(String taskName, LocalDate start, LocalDate end) {
        super(taskName);
        this.tag = "E";
        this.start = start;
        this.end = end;
    }

    public Event(String taskName, boolean isDone, LocalDate start, LocalDate end) {
        super(taskName, isDone);
        this.tag = "E";
        this.start = start;
        this.end = end;
    }

//    @Override
//    String getFromTime() {
//        return this.start;
//    }
//
//    @Override
//    String getToTime() {
//        return this.end;
//    }
    @Override
    public LocalDate getStart() {
        return this.start;
    }

    @Override
    public LocalDate getEnd() {
        return this.end;
    }

    @Override
    public String toString() {
        String startFormat = this.start.format(
                DateTimeFormatter.ofPattern("MMM d yyyy", new Locale("en"))
        );
        String endFormat = this.end.format(
                DateTimeFormatter.ofPattern("MMM d yyyy", new Locale("en"))
        );
        return "[E]" + super.toString() + " (from: " +
                startFormat + " to: " + endFormat + ")";
    }
}
