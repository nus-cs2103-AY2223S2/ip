package duke;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeConvertor {
    private LocalDateTime realDate;

    public TimeConvertor(String timing) {
        this.realDate = LocalDateTime.parse(timing, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public TimeConvertor(String timing, String type) {
        this.realDate = LocalDateTime.parse(timing, DateTimeFormatter.ofPattern(type));
    }

    public String getDate() {
        return realDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public String getDate(String type) {
        return realDate.format(DateTimeFormatter.ofPattern(type));
    }

    @Override
    public String toString() {
        return realDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma"));
    }
}