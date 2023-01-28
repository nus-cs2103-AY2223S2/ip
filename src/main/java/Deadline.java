import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
<<<<<<< HEAD

    protected String by;
=======
    protected LocalDate by;
>>>>>>> branch-Level-8

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("dd-MMM-YYYY (EEE)")) + ")";
    }


}
