import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private LocalDate startTime;
    private LocalDate endTime;

    public Event(String des, String startTime, String endTime) throws InvalidDateException {
        super(des);
        try {
            this.startTime = LocalDate.parse(startTime);
            this.endTime = LocalDate.parse(endTime);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException();
        }
    }

    @Override
    public String getStatusIcon() {
        return String.format("[E]%s | FROM: %s TO: %s",
                super.getStatusIcon(),
                this.startTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                this.endTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}