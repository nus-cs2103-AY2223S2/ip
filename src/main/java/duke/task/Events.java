package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Events Task.
 * @author pzhengze
 */
public class Events extends Task {
    /** Storage of the start time of Event. */
    private final LocalDateTime startTime;

    /** Storage of the end time of Event. */
    private final LocalDateTime endTime;

    /** String form of startTime. */
    private final String stringStartTime;

    /** String form of endTime */
    private final String stringEndTime;

    /**
     * Constructor for Events object where duration is represented by LocalDateTime objects.
     * @param description The description of the Task.
     * @param isDone The boolean showing if the Task has been done.
     * @param startTime The start time of the Event Task.
     * @param endTime The end time of the Event Task.
     */
    public Events(String description, boolean isDone, LocalDateTime startTime, LocalDateTime endTime) {
        super(description, isDone);
        this.startTime = startTime;
        this.stringStartTime = startTime.format(DateTimeFormatter.ofPattern("d MMM yyyy HHmm"));
        this.endTime = endTime;
        this.stringEndTime = endTime.format(DateTimeFormatter.ofPattern("d MMM yyyy HHmm"));
    }

    /**
     * Constructor for Events object where duration is represented by Strings.
     * @param description The description of the Task.
     * @param isDone The boolean showing if the Task has been done.
     * @param startTime The start time of the Event Task.
     * @param endTime The end time of the Event Task.
     */
    public Events(String description, boolean isDone, String startTime, String endTime) {
        super(description, isDone);
        this.startTime = null;
        this.stringStartTime = startTime;
        this.endTime = null;
        this.stringEndTime = endTime;
    }

    /**
     * Returns the Events Task in String form.
     * This shows if the Events Task has been done, represented by [X].
     * @return The Events Task in String form.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), stringStartTime, stringEndTime);
    }

    /**
     * Returns the Task in String form specific for saving.
     * @return The Task in String form
     */
    @Override
    public String save() {
        return String.format("event %s-%s-%s-%s\n",
                this.description,
                this.isDone,
                this.startTime.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm")),
                this.endTime.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm")));
    }
}
