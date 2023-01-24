package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import duke.exceptions.InvalidDateException;

public class Event extends Task {
    private LocalDate startDate;
    private LocalDate endDate;

    public Event(String des, String startTime, String endTime) throws InvalidDateException {
        super(des);
        try {
            this.startDate = LocalDate.parse(startTime);
            this.endDate = LocalDate.parse(endTime);
            if (this.startDate.compareTo(this.endDate) > 0) { // invalid end date before start
                throw new InvalidDateException();
            }
        } catch (DateTimeParseException e) {
            throw new InvalidDateException();
        }
    }

    @Override
    public String getStatusIcon() {
        return String.format("[E]%s | FROM: %s TO: %s",
                super.getStatusIcon(),
                this.startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                this.endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Override
    public String encode() {
        return String.format("%s ### %s ### %s ### %s", "event", super.encode(), this.startDate, this.endDate);
    }

    @Override
    public boolean fallsOnDate(LocalDate date) {
        return this.startDate.compareTo(date) * date.compareTo(this.endDate) >= 0;
    }
}