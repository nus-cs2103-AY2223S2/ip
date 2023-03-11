package tasks;

import java.time.LocalDateTime;

import parser.Parser;

/**
 * This class is used to represent an Event task.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime until;

    /**
     * Constructor for the Event.
     * @param description The description for the Event.
     * @param from The starting date of the Event.
     * @param until The ending date of the Event.
     */
    public Event(String description, String from, String until) {
        super(description);
        this.from = Parser.parseDate(from);
        this.until = Parser.parseDate(until);
    }

    /**
     * Returns the starting date of the Event in LocalDateTime.
     * @return The starting date of the Event in LocalDateTime.
     */
    public LocalDateTime getFrom() {
        return this.from;
    }

    /**
     * Returns the ending date of the Event in LocalDateTime.
     * @return The ending date of the Event in LocalDateTime.
     */
    public LocalDateTime getUntil() {
        return this.until;
    }

    /**
     * Sets the starting date of the Event to the user input.
     * @param userInput The new starting date.
     */
    public void setFrom(LocalDateTime userInput) {
        this.from = userInput;
    }

    /**
     * Sets the ending date of the Event to the user input.
     * @param userInput The new ending date.
     */
    public void setUntil(LocalDateTime userInput) {
        this.until = userInput;
    }

    /**
     * Returns the string representation of the Event.
     * @return The string representation of the Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from.getMonth().toString().substring(0, 3)
            + " " + this.from.getDayOfMonth() + " " + this.from.getYear() + ", " + this.from.toLocalTime() + " | to: "
            + this.until.getMonth().toString().substring(0, 3) + " " + this.until.getDayOfMonth() + " "
            + this.until.getYear() + ", " + this.until.toLocalTime() + ")";
    }
}
