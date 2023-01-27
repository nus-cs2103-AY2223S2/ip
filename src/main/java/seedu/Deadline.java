package seedu;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, String by) {
        super(description);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        this.by = LocalDateTime.parse(by, format);

    }

    @Override
    public String toString() {
        String dateFormat = this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        return "[D]" + super.toString() + "(by:" + dateFormat + ")";
    }
    @Override
    public String toStoreString() {
        return "D | " + super.toStoreString() + " | " + this.by + "\n";
    }

}



