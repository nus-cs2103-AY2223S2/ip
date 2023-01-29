package Duke.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents the Event Task of the Chat bot.
 */
public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    /**
     * Instantiates an Event Object that can be placed into the TaskList.
     *
     * @param description The description of the task.
     * @param from The start date of the event.
     * @param to The end date of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.to = LocalDate.parse(to.trim());
        this.from = LocalDate.parse(from.trim());
    }

    public LocalDate getFrom() {
        return this.from;
    }

    public LocalDate getTo() {
        return this.to;
    }

    /**
     * Process the current Event object to be saved into a text file.
     *
     * @return a String representing the Event to be saved.
     */
    @Override
    public String toSave() {
        if (super.isDone == true) {
            return String.format("E | 1 | %s | %s | %s\n", super.getDescription(), this.from, this.to);
        }
        return String.format("E | 0 | %s | %s | %s\n", super.getDescription(), this.from, this.to);
    }

    /**
     * Process the current Event object to be displayed.
     *
     * @return a String representing the Event to be displayed by the User Interface.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

}
