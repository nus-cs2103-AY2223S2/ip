package dudu.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Event extends Task{
    private LocalDate from;
    private LocalDate to;
    private String name;

    /**
     * Constructor for event task, marked as undone.
     *
     * @param name Name of the event task
     * @param from Starting date
     * @param to Ending date
     */
    public Event(String name, String from, String to) {
        super(name, false);
        this.name = name;
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    /**
     * Constructor for event task.
     *
     * @param name Name of the event task
     * @param from Starting date
     * @param to Ending date
     * @param isDone Completing status of the task
     */
    public Event(String name, String from, String to, boolean isDone) {
        super(name, isDone);
        this.name = name;
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    @Override
    public String getType() {
        return "Event";
    }

    @Override
    public String getStatus() {
        return isDone() ? "1" : "0";
    }

    @Override
    public String getDescription() {
        return name + " | " + from + " | " + to;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
