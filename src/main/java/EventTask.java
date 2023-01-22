import java.time.LocalDate;

public class EventTask extends Task{
    protected LocalDate from;
    protected LocalDate to;

    /**
     * Constructor to create a new instance of Task.
     * Tasks created are by default not completed.
     *
     * @param description Title of the task
     */
    public EventTask(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
