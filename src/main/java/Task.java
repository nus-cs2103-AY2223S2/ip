import java.time.DateTimeException;
import java.time.LocalDateTime;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public LocalDateTime handleDateTime(String dateTime) throws InvalidDateTimeException {
        try {
            String[] period = dateTime.split("/", 3);
            String[] yearTime = period[2].split(" ");
            int year = Integer.parseInt(yearTime[0]);
            int month = Integer.parseInt(period[1]);
            int day = Integer.parseInt(period[0]);
            int hour = Integer.parseInt(yearTime[1].substring(0, 2));
            int min = Integer.parseInt(yearTime[1].substring(2));
            return LocalDateTime.of(year, month, day, hour, min);
        } catch (NumberFormatException | IndexOutOfBoundsException | DateTimeException e) {
            throw new InvalidDateTimeException();
        }

    }

    @Override
    public String toString() {
        return String.format("[%s] %s",this.getStatusIcon(), this.description);
    }
}
