package duke.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.enums.TaskType;

/**
 * Represents the Event task.
 * Can specify the start date and end date for a task.
 */
public class Event extends Task {
    /** Event attributes **/
    protected LocalDate from;
    protected LocalDate to;

    /** Date formatter **/
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");

    /**
     * Initializes the Event task.
     *
     * @param description The description of the task.
     * @param from The start date.
     * @param to The end date.
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
        this.taskType = TaskType.EVENT;
    }

    /**
     * Overloaded constructor which accepts a string array of flags instead.
     *
     * @param description Description of the task.
     * @param flags The flags required to specify the dates.
     */
    public Event(String description, String ...flags) {
        super(description);
        this.from = LocalDate.parse(flags[0]);
        this.to = LocalDate.parse(flags[1]);
        this.taskType = TaskType.EVENT;
    }

    /**
     * Serializes the task.
     *
     * @return Returns serialized representation.
     */
    public SerializableTask serialize() {
        String flags = from + " to " + to;
        return new SerializableTask(TaskType.EVENT, isDone, description, flags);
    }

    @Override
    public boolean isActiveOn(LocalDate date) {
        boolean isBetweenDuration = date.isAfter(from) && date.isBefore(to);
        boolean isOnDuration = date.isEqual(from) || date.equals(to);
        return isBetweenDuration || isOnDuration;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(formatter) + " to: " + to.format(formatter) + ")";
    }
}
