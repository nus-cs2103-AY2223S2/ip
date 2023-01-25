import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Deadline extends Task {

    private LocalDateTime dateBy;

    public Deadline(String title, LocalDateTime dateBy) {
        super(title);
        this.dateBy = dateBy;
    }

    @Override
    public String toString() {
        String doneString = this.getDone() ? "X" : " ";
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("EEEE, MMM dd, yyyy HH:mm a");
        return String.format("[D][%s] %s (by: %s)", doneString, this.getTitle(), this.dateBy.format(dateFormat));
    }

}
