package timeslot;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Represents a time block during which the user has no scheduled tasks
 */
public class FreeTimeBlock {

    private final LocalDateTime start;
    private final LocalDateTime end;

    /**
     * Creates a new FreeTimeBlock object with the given start and end times.
     *
     * @param start the start time
     * @param end the end time
     */
    public FreeTimeBlock(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
    }


    /**
     * Returns the time difference in seconds between the start and end times of this FreeTimeBlock.
     *
     * @return the time difference in seconds
     */
    private int getTimeDifference() {
        if (hasNoBounds()) {
            return -1;
        }

        LocalDateTime newStart = LocalDateTime.now();
        long difference;

        difference = Math.abs(Duration.between(Objects.requireNonNullElse(start, newStart), end).toSeconds());

        return Math.toIntExact(difference);
    }

    /**
     * Returns whether the duration of this FreeTimeBlock is sufficient for the desired free time.
     *
     * @param desiredFreeTime the desired free time in seconds
     * @return true if the duration matches or exceeds desired free time, false otherwise
     */
    public boolean isValidSlot(int desiredFreeTime) {
        if (hasNoBounds()) {
            return true;
        }
        return this.getTimeDifference() >= desiredFreeTime;
    }

    /**
     * Determines whether this FreeTimeBlock has no time bounds.
     * A FreeTimeBlock is considered to have no bounds if either its start or end time is null.
     * @return true if this FreeTimeBlock has no time bounds, false otherwise.
     */
    private boolean hasNoBounds() {
        if (start == null && end != null) {
            return false;
        }
        return (start == null || end == null);
    }

    /**
     * Returns a string describing this FreeTimeBlock.
     *
     * @return a string describing this FreeTimeBlock
     */
    @Override
    public String toString() {
        return "FreeTimeBlock{"
                + "start=" + start
                + ", end=" + end
                + '}';
    }

    /**
     * Returns a user-friendly description of this FreeTimeBlock.
     *
     * @return a user-friendly description of this FreeTimeBlock
     */
    public String describeSelf() {

        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("h:mma");
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("EEEE, dd/M/yyyy");

        if (start == null && end == null) {
            return "Wooks wike you are fwee anytime from now!";
        }

        if (start != null && end == null) {
            String startTime = start.format(timeFormat);
            String date = start.format(dateFormat);
            return String.format("Wooks wike you are free from %s starting %s UwU!", startTime, date);
        }

        if (start == null) {
            String endTime = end.format(timeFormat);
            String date = end.format(dateFormat);
            return String.format("Wooks wike you are free from now until %s starting %s UwU!", endTime, date);
        }

        String startTime = start.format(timeFormat);
        String endTime = end.format(timeFormat);
        String date = start.format(dateFormat);
        return String.format("Wooks wike you are free between %s and %s on %s UwU!", startTime, endTime, date);
    }
}
