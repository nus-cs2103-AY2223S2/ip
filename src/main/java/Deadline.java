import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected String by;
    LocalDate dateTime;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
        this.dateTime = LocalDate.parse(by.trim(), formatter);
    }

    @Override
    public String toString() {
        return "\t[D]" + super.toString() + " (by: " + dateTime.format(formatter) + ")";
    }

    public String saveFormat(){
        return String.format("D | %s | %s", super.saveFormat(), this.by);
    }
}

