package Duke.Tasks;


import java.time.LocalDate;
import java.time.Period;

import Duke.Exceptions.InvalidDateFormatExceptions;

public class Deadline extends Task {

    protected String dueDate;
    protected LocalDate localDate;

    /**
     * The constructor of Deadline class, uses to create Deadline instance.
     *
     * @param description The description or the name of the task.
     * @param dueDate The deadline of the task.
     * @throws InvalidDateFormatExceptions
     */
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

    /**
     * Check whether the task is near the deadline(3 days).
     *
     * @return Return true if the task is not done yet and
     *         is going to due soon in 3 days.
     */
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

    /**
     * Print the task into the specific file.
     *
     * @return Return the information of the task.
     */
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
