package voile.model.task;

import java.time.LocalDate;
import java.util.Objects;

import voile.common.exception.VoileIllegalArgumentException;
import voile.util.Conversions;

/**
 * Represents an event task in the application.
 */
public class EventTask extends Task {

    private static final long serialVersionUID = -150197333726686918L;

    private LocalDate startTime;
    private LocalDate endTime;

    /**
     * Creates a new {@code EventTask} with the given description, start time and end time.
     *
     * @param description the given description
     * @param startTime the given start time
     * @param endTime the given end time
     * @throws VoileIllegalArgumentException if the description is empty, or if the start time is
     *         after the end time
     */
    public EventTask(String description, LocalDate startTime, LocalDate endTime) {
        super(description);
        if (startTime.isAfter(endTime)) {
            throw new VoileIllegalArgumentException(
                    "Oh no! An event's start time cannot be after its end time.");
        }
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return String.format(
                "[E]%s (from: %s to: %s)",
                super.toString(),
                Conversions.showDate(startTime),
                Conversions.showDate(endTime));
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof EventTask)) {
            return false;
        }
        EventTask task = (EventTask) obj;
        return super.equals(obj)
                && Objects.equals(startTime, task.startTime)
                && Objects.equals(endTime, task.endTime);
    }
}
