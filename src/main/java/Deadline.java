import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected Object by;

    public Deadline(String description, String by) throws DukeException {
        super(description);
        this.by = by;
        if (description.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
    }

    @Override
    public void isDate() {
        try {
            LocalDate d1 = LocalDate.parse((String) by);
            this.by = d1;
            by = ((LocalDate) by).format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (Exception e) {
        }
    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by + ")";
    }
}

