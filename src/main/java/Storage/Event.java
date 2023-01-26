package storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String task, LocalDateTime from, LocalDateTime to) {
        super(task);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + status() + getTask() + " (" + duration() + ")";
    }

    private String duration() {
        DateTimeFormatter formatterOne = DateTimeFormatter.ofPattern("EEE, MMM dd, hh:mm a");
        DateTimeFormatter formatterTwo = DateTimeFormatter.ofPattern("hh:mm a");
        if (sameDate(this.from, this.to)) {
            return " (" + formatterOne.format(this.from) + " - " + formatterTwo.format(this.to) + ")";
        }
        return " (" + formatterOne.format(this.from) + " - " + formatterOne.format(this.to) + ")";
    }

    private boolean sameDate(LocalDateTime dtOne, LocalDateTime dtTwo) {
        boolean sameYear = dtOne.getYear() == dtTwo.getYear();
        return sameYear && dtOne.getDayOfYear() == dtTwo.getDayOfYear();
    }

    @Override
    public String saveFormat() {
        return "[E]" + status() + getTask() + " | " + duration() + "\n";
    }
}
