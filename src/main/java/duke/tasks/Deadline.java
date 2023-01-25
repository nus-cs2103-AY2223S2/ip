package duke.tasks;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected String by;
    LocalDateTime dateTime;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
        try {
            this.dateTime = LocalDateTime.parse(by.trim(),formatter);
        } catch (DateTimeParseException e) {
            System.out.println("\tPlease enter a date in e.g yyyy-mm-dd 23:59 format!");
        }
    }

    @Override
    public String toString() {
        return "\t[D]" + super.toString() + " (by: " + dateTime.format(formatter) + ")";
    }

    public String saveFormat(){
        return String.format("D | %s | %s", super.saveFormat(), this.by);
    }
}

