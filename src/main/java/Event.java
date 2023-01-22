import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected String type = "[ E ]";
    protected LocalDate fromDate;
    protected LocalDate toDate;
    public Event(String description, String fromDate, String toDate) {
        super(description);
        this.fromDate = LocalDate.parse(fromDate);
        this.toDate = LocalDate.parse(toDate);
    }


    @Override
    public String toString() {
        return type + super.toString() + "from: " + this.fromDate + " to: " + this.toDate;
    }
}
