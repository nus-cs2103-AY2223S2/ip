import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime dueDate;

    public Deadline(String name, LocalDateTime dueDate) {
        super(name);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                this.dueDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy',' hh'.'mma")) + ")";
    }

    @Override
    public String toFormatString() {
        return "D | " + (super.done ? "1" : "0") + " | " + super.name + " | " +
                this.dueDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy',' hh'.'mma"));
    }
}
