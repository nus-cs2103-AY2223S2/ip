import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{

    protected String by;
    LocalDate byDate;

    public Deadline(String desc, String by) {
        super(desc);
        this.by = by;
        byDate = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
