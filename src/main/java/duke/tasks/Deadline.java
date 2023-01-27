package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDateTime dateBy;

    public Deadline(String title, LocalDateTime dateBy, boolean done) {
        super(title, done);
        this.dateBy = dateBy;
    }

    @Override
    public String toString() {
        String doneString = this.getDone() ? "X" : " ";
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("EEEE, MMM dd, yyyy HH:mm a");
        return String.format("[D][%s] %s (by: %s)", doneString, this.getTitle(), this.dateBy.format(dateFormat));
    }

    public String convertToMemoryString() {
        String doneString = this.getDone() ? "1" : "0";
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("EEEE MMM dd yyyy HH:mm a");
        return "D, " + doneString + ", " + this.getTitle() + ", " + this.dateBy.format(dateFormat);
    }

}
