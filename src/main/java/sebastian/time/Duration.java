package sebastian.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class representing a duration
 */
public class Duration {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructor
     * @param from start time
     * @param to end time
     */
    public Duration(LocalDateTime from, LocalDateTime to) {
        this.from = from;
        this.to = to;
    }

    /**
     * Checks if the start date and ld are on the same day
     * @param ld the other date
     * @return whether the start date is equal to the other date
     */
    public boolean isSameDay(LocalDate ld) {
        return ld.equals(from.toLocalDate());
    }

    /**
     * Update the start time of the duration
     * @param from the new start time
     * @return the updated duration
     */
    public Duration updateFromTime(LocalDateTime from) {
        this.from = from;
        return this;
    }

    /**
     * Update the end time of the duration
     * @param to the new end time
     * @return the updated duration
     */
    public Duration updateEndTime(LocalDateTime to) {
        this.to = to;
        return this;
    }

    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DatePattern.PRESENTATION_FORMAT.toString());
        return "(from: " + from.format(dtf) + " to: " + to.format(dtf) + ")";
    }

    /**
     * Format the duration into a suitable String representation to be written to the hard disk
     * @return the formatted String representation
     */
    public String formatForSave() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DatePattern.USER_INPUT_FORMAT.toString());
        return dtf.format(from) + "<>" + dtf.format(to);
    }
}
