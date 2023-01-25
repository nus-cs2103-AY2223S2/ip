import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected LocalDateTime start;
    protected LocalDateTime end;

    public Event(String name, String start, String end) {
        super(name);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yy-HHmm");
        start = start.substring(0, start.length() - 1);
        this.start = LocalDateTime.parse(start, format);
        this.end = LocalDateTime.parse(end, format);
    }
    public Event(String name, String isDone, String start, String end) {
        super(name, isDone);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy-HHmm");
        this.start = LocalDateTime.parse(start, formatter);
        this.end = LocalDateTime.parse(end, formatter);;
    }
    

    public String getEnd() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy-HHmm");
        return end.format(formatter);
    }

    public String getStart() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy-HHmm");
        return start.format(formatter);
    }

    @Override
    public String saveFormat() {
        return "E;" + this.name + ";" +this.isDone + ";" + this.getStart() + ";" + this.getEnd();
    }

    @Override
    public String toString() {
        String box;
        if (this.getDone()) {
            box = "[X] ";
        } else {
            box = "[ ] ";
        }
        return"[E]" + box + this.getName() + "(from: " + this.getStart() + " to " + this.getEnd() + ")";
    }
}
