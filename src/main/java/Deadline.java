import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


public class Deadline extends Task {

     LocalDate deadline;

    public Deadline(String description, String deadline) {
        super(description);
        
        this.deadline = LocalDate.parse(deadline);

    }

    @Override
    public String toString() {
        return (isDone? "[D][X] " : "[D][ ] ") + description + (". Deadline: "  + this.deadline);
    }
}
