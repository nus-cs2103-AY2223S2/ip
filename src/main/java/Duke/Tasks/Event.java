package Duke.Tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected String startingTime;
    protected String endingTime;
    protected LocalDate localStartingDate;

    public Event(String description, String startingTime, String endingTime) throws DateTimeParseException {
        super(description);
        this.startingTime = startingTime;
        this.endingTime = endingTime;
        this.localStartingDate = LocalDate.parse(startingTime);
    }

    public boolean isComingSoon() {
        if (this.done) {
            return false;
        } else {
            LocalDate currentTime = LocalDate.now();
            Period interval = Period.between(currentTime, localStartingDate);
            int years = interval.getYears();
            int months = interval.getMonths();
            int days = interval.getDays();
            if (years == 0 && months == 0 && days <= 7) {
                return true;
            }
            return false;
        }
    }

    @Override
    public String printTask() {
        return String.format("E | %d | %s | %s-%s ", isComplete ? 1 : 0, description, startingTime, endingTime);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (" + localStartingDate.getMonth() + " "
                + localStartingDate.getDayOfMonth() + " " + localStartingDate.getYear() + ")";
    }
}
