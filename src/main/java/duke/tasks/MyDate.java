package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a date in a specified format.
 */
public class MyDate {
    protected static DateTimeFormatter saveFormat = DateTimeFormatter.ofPattern(("dd/MM/yyyy"));
    protected static DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("yyyy MMM dd");

    protected LocalDate date;
    public MyDate(String date) {
        this.date = getValidDate(date);
    }

    public LocalDate getValidDate(String date) {
        return LocalDate.parse(date, MyDate.saveFormat);
    }

    /**
     * Checks if a given string is a valid date in the specified format.
     * @param date The given string.
     * @return True if the string is a valid date, else false.
     */
    public static boolean isValidDate(String date) {
        try {
            LocalDate.parse(date, MyDate.saveFormat);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    public String printDateTime() {
        return this.date.format((outputFormat));
    }

    public boolean isEqual(LocalDate current) {
        return this.date.isEqual(current);
    }

    public boolean isBetween(LocalDate f, LocalDate t) {
        return !this.date.isBefore(f) && !this.date.isAfter(t);
    }
}
