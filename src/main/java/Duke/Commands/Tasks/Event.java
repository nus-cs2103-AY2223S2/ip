package Duke.Commands.Tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;
    public Event(String description, String from, String to) {
        this(description, false, from, to);
    }

    public Event(String description, boolean isDone, String from, String to) {
        super(description, isDone);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    /**
     * Generates a letter representing the type of task
     *
     * @return a letter representing the type of this task
     */
    public String getTaskClass() {
        return "T";
    }

    /**
     * Generates a String to store this task in a local text file
     *
     * @return A representative String that contains data about the current task
     */
    public String generateStorageText() {
        return String.format("%s-%s-%s-%s-%s",
                this.getTaskClass(), this.getStatusIcon(),
                this.getDescription(), this.from.toString(), this.to.toString());
    }

    private String getFrom() {
        return this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    private String getTo() {
        return this.to.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    public String toString() {
        return String.format("[%s][%s] %s (from: %s to: %s)",
                this.getTaskClass(), this.getStatusIcon(),
                this.description, this.getFrom(), this.getTo());
    }
}
