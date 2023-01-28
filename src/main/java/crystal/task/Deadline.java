package crystal.task;

import crystal.CrystalException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Deadline extends Task {

    public LocalDateTime by;

    public Deadline(String description, String by) throws CrystalException {
        super(description);

        try {
            LocalDateTime date = LocalDateTime.parse(by.trim());
            this.by = date;
        } catch (Exception e) {
            throw new CrystalException("Please change the input date format to yyyy-MMM-dThh:mm!");
        }

    }



    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy hhmm a")) + ")";
    }

    @Override
    public String toPrint() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
