import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private String startTime = "";
    private String endTime = "";
    private LocalDate startTimeParsed;
    private LocalDate endTimeParsed;

    public Event(int id, String task, String startTime, String endTime) {
        super(id, task);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Event(int id, String task, LocalDate startTime, LocalDate endTime) {
        super(id, task);
        this.startTimeParsed = startTime;
        this.endTimeParsed = endTime;
    }

    @Override
    public String printTask() {
        return this.isDone()
                ? "[E][x] " + this.getTask() + "(Start: "
                        + this.startTimeParsed.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                        + " | End: "
                        + endTimeParsed.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")"
                : "[E][ ] " + this.getTask() + "(Start: "
                        + this.startTimeParsed.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                        + " | End: "
                        + this.endTimeParsed.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
