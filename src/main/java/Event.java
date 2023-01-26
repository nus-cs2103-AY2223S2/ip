import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task{
    private LocalDate startDate;
    private LocalDate endDate;

    public Event(String name, String startDate, String endDate) throws DukeException {
        super(name);
        try {
            this.startDate = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            this.endDate = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            if (this.startDate.isAfter(this.endDate)) {
                throw new DukeException("The starting date of this event is after its ending date!");
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("Please enter a valid date format in dd/mm/yyyy!");
        }
    }

    public String getStartDate() {
        return this.startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public String getEndDate() {
        return this.endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(Starts from " + this.getStartDate() + "; ends at " + this.getEndDate() + ")";
    }
}
