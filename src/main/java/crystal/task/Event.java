package crystal.task;

import crystal.CrystalException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;



    public Event(String description, String from, String to) throws CrystalException {
        super(description);
        try {
            this.from = LocalDateTime.parse(from.trim());
            this.to = LocalDateTime.parse(to.trim());
        } catch (Exception e) {
            throw new CrystalException("Please change the input date format to yyyy-MMM-dThh:mm!");
        }

    }


    @Override
    public String toString() {

        return "[E]" + super.toString() + " (from: " + this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy hhmm a")) + " to: "
                + this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy hhmm a")) + ")";
    }

    @Override
    public String toPrint() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
