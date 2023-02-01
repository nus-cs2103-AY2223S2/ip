package duke;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
/**
 * Object class for time
 * Input of time will be formatted to MMM d yyyy hh:mm
 */
public class Times {
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    protected LocalDateTime d;

    /**
     * Constructor of Times
     *
     * @param time (format:yyyy-mm-dd hhmm) (eg. 2019-10-15 1530)
     */
    Times(String time) throws DateTimeParseException {
        try {
            d = LocalDateTime.parse(time, FORMATTER);
        } catch (DateTimeParseException e) {
            System.out.println("Input String is not correct format for time");
        }
    }

    @Override
    public String toString() {
        return d.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
    }
}
