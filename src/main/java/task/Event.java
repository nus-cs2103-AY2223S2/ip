package task;

import java.time.LocalDate;

public class Event extends Task{
    private LocalDate startDetails;
    private LocalDate endDetails;
    static final String divider = " | ";

    public Event(String name, LocalDate startDetails, LocalDate endDetails) {
        super(name);
        this.startDetails = startDetails;
        this.endDetails = endDetails;
    }

    @Override
    public String toSaveFormat() {
        return "E" + super.toSaveFormat() + divider + startDetails + divider + endDetails;
    }


    @Override
    public String toString() {
        return "[E]" + super.toString() + "(" + startDetails + " : " + endDetails + ")";
    }


}
