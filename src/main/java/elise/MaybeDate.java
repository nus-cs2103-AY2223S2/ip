package elise;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Abstraction of a period represented by either String or date and time.
 */
public class MaybeDate {

    private final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("LLL dd yyyy HH:mm");
    private String timeString;
    private LocalDateTime dateTime;
    private final boolean isTime;

    /**
     * Constructor for MaybeDate as String representation.
     *
     * @param timeString String of this period.
     */
    public MaybeDate(String timeString) {
        isTime = false;
        this.timeString = timeString;
    }

    /**
     * Constructor for MaybeDate as date and time.
     *
     * @param dateTime Date and time of this period.
     */
    public MaybeDate(LocalDateTime dateTime) {
        isTime = true;
        this.dateTime = dateTime;
    }

    /**
     * Returns String representation of this period.
     *
     * @return String representation of this period.
     */
    @Override
    public String toString() {
        if (isTime) {
            return dateTime.format(OUTPUT_FORMAT);
        } else {
            return timeString;
        }
    }

}
