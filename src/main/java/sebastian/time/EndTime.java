package sebastian.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The end time
 */
public class EndTime {
    private LocalDateTime endTime;

    public EndTime(LocalDateTime endtime) {
        this.endTime = endtime;
    }

    /**
     * Checks if two this and ld are on the same day
     * @param ld the other date
     * @return whether this date is equal to the other date
     */
    public boolean isSameDay(LocalDate ld) {
        return ld.equals(endTime.toLocalDate());
    }

    /**
     * Update the end time
     * @param endTime the updated endTime
     */
    public void updateEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DatePattern.PRESENTATION_FORMAT.toString());
        return "(by: " + endTime.format(dtf) + ")";
    }

    /**
     * Format the duration into a suitable String representation to be written to the hard disk
     * @return the formatted String representation
     */
    public String formatForSave() {
        return DateTimeFormatter.ofPattern(DatePattern.USER_INPUT_FORMAT.toString()).format(endTime);
    }

}
