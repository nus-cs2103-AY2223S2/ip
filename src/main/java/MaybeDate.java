import java.time.LocalDateTime;

public class MaybeDate {
    String timeString;
    LocalDateTime dateTime;
    boolean isTime;

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
            return dateTime.toString();
        } else {
            return timeString;
        }
    }

}
