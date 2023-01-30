package Tasks;


import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected String dueDate;
    protected LocalDate localDate;

    public Deadline(String description, String dueDate) throws DateTimeParseException {
        super(description);
        this.dueDate = dueDate;
        this.localDate = LocalDate.parse(dueDate);
    }

    public boolean isDueSoon() {
        if (this.done) {
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
    public String toString() {
        return "[D]" + super.toString() + " (" + localDate.getMonth() + " "
                + localDate.getDayOfMonth() + " " + localDate.getYear() + ")";
    }
}
