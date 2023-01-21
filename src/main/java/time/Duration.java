package time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A duration
 */
public class Duration {
    private LocalDateTime from;
    private LocalDateTime to;

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

    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DatePattern.PRESENTATION_FORMAT.toString());
        return "(from: " + from.format(dtf) + " to: " + to.format(dtf) + ")";
    }
}
