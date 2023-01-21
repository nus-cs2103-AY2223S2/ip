package duke;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimedTask extends Task{
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    LocalDateTime start;
    String startString;
    LocalDateTime end;
    String endString;

    public TimedTask(boolean status, String des) {
        super(status, des);

    }

    public void setStart(String s) {
        if (s == null) {
            return;
        }
        this.start = dateTimeParse(s);
        this.startString = start.format(formatter);
    }

    public void setEnd(String s) {
        this.end = dateTimeParse(s);
        this.endString = end.format(formatter);
    }

    public LocalDateTime dateTimeParse(String s) {
        return LocalDateTime.parse(s,formatter);
    }

    public String toStringStart() {
        return this.startString;
    }

    public String toStringEnd() {
       return this.endString;
    }
}
