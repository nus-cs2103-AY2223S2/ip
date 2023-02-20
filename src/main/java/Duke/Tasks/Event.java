package Duke.Tasks;

import Duke.Exceptions.InvalidDateFormatExceptions;

import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.Period;


public class Event extends Task {
    protected String startingTime;
    protected String endingTime;
    protected LocalDate localStartingDate;
    protected LocalDate localEndingDate;

    public Event(String description, String startingTime, String endingTime)
            throws DateTimeParseException {
        super(description);
        this.startingTime = startingTime;
        this.endingTime = endingTime;
        try {
            this.localStartingDate = LocalDate.parse(startingTime);
            this.localEndingDate = LocalDate.parse(endingTime);
        } catch (InvalidDateFormatExceptions e) {
            String errMsg = "Parse error: " + e.getMessage() +"\n" +
                    "\tPlease try again with the correct format \"YYYY-MM-DD\"";
            throw new InvalidDateFormatExceptions(errMsg);
        }
        this.localStartingDate = LocalDate.parse(startingTime);
    }

    /**
     * Check whether the event is near starting date(one week before).
     *
     * @return Return true if the task is not done yet and
     *         the event is one week from now.
     */
    public boolean isComingSoon() {
        if (this.isComplete) {
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

    /**
     * Print the task into the specific file.
     *
     * @return Return the information of the task.
     */
    @Override
    public String printTask() {
        return String.format("E | %d | %s | %s-%s ", isComplete ? 1 : 0,
                description, startingTime, endingTime);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (" + localStartingDate.getDayOfMonth() + "/"
                + localStartingDate.getMonth() + "/"
                + localStartingDate.getYear() + " to "
                + localEndingDate.getDayOfMonth() + "/"
                + localEndingDate.getMonth() + "/"
                + localEndingDate.getYear() +")";
    }
}
