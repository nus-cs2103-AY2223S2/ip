package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    LocalDate start;
    LocalDate end;

    public Event(String type, String detail, boolean marked, String start, String end){
        super(type, detail, marked);
        this.start = LocalDate.parse(start);
        this.end = LocalDate.parse(end);
    }

    public Event(String type, String detail, String start, String end){
        super(type, detail);
        this.start = LocalDate.parse(start);
        this.end = LocalDate.parse(end);
    }

   /* Deadline(String type, String detail){
        super(type, detail);
    }*/

    @Override
    public String toString(){
        if (marked) {
            return "[E][X] " + super.detail + " (from: " + this.start.format(DateTimeFormatter.ofPattern("h, MMM dd yyyy")) + " to: " + this.end.format(DateTimeFormatter.ofPattern("h, MMM dd yyyy")) + ")";
        } else {
            return "[E][ ] " + super.detail + " (from: " + this.start.format(DateTimeFormatter.ofPattern("h, MMM dd yyyy")) + " to: " + this.end.format(DateTimeFormatter.ofPattern("h, MMM dd yyyy")) + ")";
        }
    }
}