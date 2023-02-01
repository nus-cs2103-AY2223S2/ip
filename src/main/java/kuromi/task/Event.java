package kuromi.task;

/**
 * Event task represented by description, start date, and end date. Extends from Task class.
 */
public class Event extends Task {
    /** The start date represented as a String **/
    protected String by;
    /** The end date represented as a String **/
    protected String from;

    /**
     * kuromi.MainWindow.kuromi.KuromiException.Main constructor (for invocation by most classes)
     * @param description The description of an event.
     * @param by The end date of an event.
     * @param from The start date of an event.
     */
    public Event(String description, String by, String from) {
        super(description);
        this.by = by;
        this.from = from;
    }

    /**
     * Secondary constructor (for invocation by Storage to put task in data into TaskList)
     *
     * @param description The description of an event.
     * @param by The end date of an event.
     * @param from The start date of an event.
     * @param isDone The status of an event.
     */
    public Event(String description, String by, String from, boolean isDone) {
        super(description, isDone);
        this.by = by;
        this.from = from;
    }

    /**
     * Get the detailed description of an event.
     * To store the current data into the file.
     *
     * @return Detailed description as a String.
     */
    @Override
    public String getDetailedDescription() {
        return super.description + " | " + this.from + " | " + this.by;
    }

    /**
     * Get the symbol of an event.
     *
     * @return The symbol of an event.
     */
    @Override
    public boolean containsKeyword(String keyword) {
        return this.description.contains(keyword);
    }
    @Override
    public String getSymbol() {
        return "E";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.by + ")";
    }

}
