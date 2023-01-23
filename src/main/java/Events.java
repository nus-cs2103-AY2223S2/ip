import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Events extends Task{
    protected LocalDateTime start;
    protected LocalDateTime end;
    private DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    public Events(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public void getDeadline() {
        System.out.println("The event is from" + start.format(format) + " to "
                + end.format(format));
    }

    public String toString() {
        return "[E]" + super.toString() + "(from: " + start.format(format) + "to: "
                + end.format(format) + ")";
    }
}
