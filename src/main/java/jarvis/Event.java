package jarvis;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDate startTimeParsed;
    private LocalDate endTimeParsed;

    public Event(int id, String task, LocalDate startTime, LocalDate endTime) {
        super(id, task);
        this.startTimeParsed = startTime;
        this.endTimeParsed = endTime;
    }

    @Override
    public String printTask() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");

        return this.isDone()
                ? "[E][x] " + this.getTask() + " (Start: "
                        + this.startTimeParsed.format(formatter)
                        + " | End: "
                        + endTimeParsed.format(formatter) + ")"
                : "[E][ ] " + this.getTask() + " (Start: "
                        + this.startTimeParsed.format(formatter)
                        + " | End: "
                        + this.endTimeParsed.format(formatter) + ")";
    }
}
