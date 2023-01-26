import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, LocalDate by) throws JamesException{
        super(description);
        this.by = by;

    }

    @Override
    public String toString() {
        String dateFormat = this.by.format(DateTimeFormatter.ofPattern("d/MM/yyyy"));
        return "[D]" + super.toString() + " (by:" + dateFormat + ")";
    }

}

