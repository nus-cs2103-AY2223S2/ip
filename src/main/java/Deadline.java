import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDateTime deadline;

    public Deadline(String taskDescription, LocalDateTime deadline) {
        super(taskDescription);
        this.deadline = deadline;
    }


    @Override
    public String toStorageFormatString() {
        return "D|" + (isDone ? "1" : "0") + "|" + this.taskDescription + "|" + this.deadline.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + ")" ;
    }

}
