package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MaybeDate {

    private final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("LLL dd yyyy HH:mm");
    private String timeString;
    private LocalDateTime dateTime;
    final boolean isTime;

    protected MaybeDate(String timeString) {
        isTime = false;
        this.timeString = timeString;
    }

    protected MaybeDate(LocalDateTime dateTime) {
        isTime = true;
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        if (isTime) {
            return dateTime.format(OUTPUT_FORMAT);
        } else {
            return timeString;
        }
    }

}
