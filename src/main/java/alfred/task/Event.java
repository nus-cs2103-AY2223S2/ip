package alfred.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task given by the user.
 */
public class Event extends Task {

    protected LocalDateTime startDate;
    protected LocalDateTime endDate;

    /**
     * Constructs an Event object that represents a unique task given by the user.
     * @param description {@inheritDoc}
     * @param startDate Provides the start date of the event.
     * @param endDate Provides the end date of the event.
     */
    public Event(String description, String startDate, String endDate) {
        super(description);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        this.startDate = LocalDateTime.parse(startDate, format);
        this.endDate = LocalDateTime.parse(endDate, format);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsDate(LocalDate date) {
        return startDate.toLocalDate().isEqual(date) || endDate.toLocalDate().isEqual(date);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String addToFile() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        String str = String.format("E | %d | %s | %s-%s",
                isDone ? 1 : 0, this.description, this.startDate.format(formatter),
                this.endDate.format(formatter));
        return str + "\n";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy h:mma");
        return String.format("[E][%s] %s(from: %s to: %s)",
                isDone ? "X" : " ", description,
                startDate.format(formatter), endDate.format(formatter));
    }
}

