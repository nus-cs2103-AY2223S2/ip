package duke.Tasks;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate deadline;

    public Deadline(int id, String description, LocalDate deadline) {
        super(id, description);
        this.deadline = deadline;
    }

    public String toFile() {
        return "D|" + this.isDone + "|" + this.desc + "|" + this.deadline;
    }

    @Override
    public String toString() {
        String statusIcon = this.getStatusIcon();
        String parsedDeadline = deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return id + ". [D][" + statusIcon + "] " + this.desc + "(" + parsedDeadline + ")";
    }
}
