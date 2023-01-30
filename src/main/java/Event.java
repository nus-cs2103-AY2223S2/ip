import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private LocalDate from;
    private LocalDate to;

    public Event(String description, LocalDate from, LocalDate to){
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + " (from "
                + this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to " + this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
