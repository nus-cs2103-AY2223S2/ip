import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;
    protected DateTimeFormatter timeFormat;

    public Deadline(String description, LocalDateTime by, Boolean isDone) {
        super(description, 'D', isDone);
        this.by = by;
        this.timeFormat = DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm");
    }

    /**
     * Returns a string representation of this deadline task
     * @return String
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by.format(timeFormat) + ")";
    }


    /**
     * Returns a string representation of what is saved in the database
     * @return String
     */
    @Override
    public String savedAs() {
        return (super.savedAs() + "|" + this.by.format(timeFormat));
    }
}