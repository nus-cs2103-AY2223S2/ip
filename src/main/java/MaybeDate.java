import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MaybeDate {

    private final DateTimeFormatter OUTPUTFORMAT =  DateTimeFormatter.ofPattern("LLL dd yyyy");
    String timeString;
    LocalDateTime dateTime;
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
            return dateTime.format(OUTPUTFORMAT);
        } else {
            return timeString;
        }
    }

}
