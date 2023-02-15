package duke.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Task that is used to handle a task with a duration.
 */
public class Event extends Task {
    private LocalDateTime fromDateTime;
    private LocalDateTime toDateTime;

    /**
     * Class constructor of Event
     * @param input the title of the event task
     * @param from the starting date and time of the event task
     * @param to the ending date and time of the event task
     */
    public Event(String input, String from, String to) {
        super(input);
        this.fromDateTime = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        this.toDateTime = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    /**
     * Returns the string representation of the event task.
     * @return the string representation of the event task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.fromDateTime.format(
                DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a")
        ) + " to: " + this.toDateTime.format(
                DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a")
        ) + ")";
    }

    /**
     * Returns the string representation of the event task in a format for saving a task into the local data file.
     * @return the string representation of the event task in a format for saving a task into the local data file
     */
    @Override
    public String toTxtString() {
        return "E" + super.toTxtString() + "|" + this.fromDateTime.format(
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
        ) + "-" + this.toDateTime.format(
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
        );
    }
}
