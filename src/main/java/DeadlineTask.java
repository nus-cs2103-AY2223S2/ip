import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {
    protected LocalDate by;

    /**
     * Constructor to create a new instance of Task.
     * Tasks created are by default not completed.
     *
     * @param description Title of the task
     */
    public DeadlineTask(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        String byString = by.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        return "[D]" + super.toString() + " (by: " + byString + ")";
    }
}
