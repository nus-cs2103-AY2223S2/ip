package leo.storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private final LocalDateTime from;
    private final LocalDateTime to;

    public Event(String task, LocalDateTime from, LocalDateTime to) {
        super(task);
        this.from = from;
        this.to = to;
        setType(TaskType.EVENT);
    }

    @Override
    public String toString() {
        return typeAndStatus() + getTask() + " (" + duration() + ")";
    }

    private String duration() {
        DateTimeFormatter formatterOne = DateTimeFormatter.ofPattern("EEE, MMM dd, hh:mm a");
        DateTimeFormatter formatterTwo = DateTimeFormatter.ofPattern("hh:mm a");
        if (sameDate(this.from, this.to)) {
            return formatterOne.format(this.from) + " - " + formatterTwo.format(this.to);
        }
        return formatterOne.format(this.from) + " - " + formatterOne.format(this.to);
    }

    private boolean sameDate(LocalDateTime dtOne, LocalDateTime dtTwo) {
        boolean sameYear = dtOne.getYear() == dtTwo.getYear();
        return sameYear && dtOne.getDayOfYear() == dtTwo.getDayOfYear();
    }

    @Override
    public String saveFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy HH:mm");
        String strFrom = formatter.format(this.from);
        String strTo = formatter.format(this.to);
        return typeAndStatus() + getTask() + " | " + strFrom + " | " + strTo + "\n";
    }
}
