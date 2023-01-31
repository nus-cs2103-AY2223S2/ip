package duke.tasks;

import duke.helper.DateTimeParser;

import java.time.LocalDate;
public class Event extends Task {


    protected LocalDate startTime;


    static final String type  = "E";


    protected LocalDate endTime;


    //getter for type
    public static String getType() {
        return type;
    }

    //getter for starttime
    public LocalDate getStartTime() {
        return this.startTime;
    }

    //getter for endtime
    public LocalDate getEndTime() {
        return this.endTime;
    }

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
