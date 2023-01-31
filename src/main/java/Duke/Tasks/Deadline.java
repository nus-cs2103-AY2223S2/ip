package Duke.Tasks;


import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;

import Exceptions.InvalidDateFormatExceptions;

public class Deadline extends Task {

    protected String dueDate;
    protected LocalDate localDate;

    public Deadline(String description, String dueDate) throws InvalidDateFormatExceptions {
        super(description);
        this.dueDate = dueDate;
        try {
            this.localDate = LocalDate.parse(dueDate);
        } catch (InvalidDateFormatExceptions e) {
            String errMsg = "Parse error: " + e.getMessage() +"\n" +
                    "\tPlease try again with the correct format \"YYYY-MM-DD\"";
            throw new InvalidDateFormatExceptions(errMsg);
        }

    }

    public boolean isDueSoon() {
        if (this.isComplete) {
            return false;
        } else {
            LocalDate currentTime = LocalDate.now();
            Period interval = Period.between(currentTime, localDate);
            int years = interval.getYears();
            int months = interval.getMonths();
            int days = interval.getDays();
            if (years == 0 && months == 0 && days <= 3) {
                return true;
            }
            return false;
        }
    }

    @Override
    public String printTask() {
        return String.format("D | %d | %s | %s ", isComplete ? 1 : 0, description, dueDate);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (" + localDate.getMonth() + " "
                + localDate.getDayOfMonth() + " " + localDate.getYear() + ")";
    }
}
