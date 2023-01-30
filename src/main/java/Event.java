import java.util.*;
import java.time.LocalDate;
public class Event extends Task {


    protected LocalDate startTime;


    static final String type  = "E";


    protected LocalDate endTime;

    public Event(String name, String startTime, String endTime) {
        super(name);
        this.startTime = DateTimeParser.parse(startTime);
        this.endTime = DateTimeParser.parse(endTime);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startTime.toString()
                + " to: " + endTime.toString() + ")";
    }
}
